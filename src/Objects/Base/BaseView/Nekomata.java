package Objects.Base.BaseView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public abstract class Nekomata extends Actor
{
    private Array<AnimacionConfig> animations = new Array<>();  //An array that contains the params of each Animation.
    private AnimacionConfig currentAnimation;   //The current animation params.
    private int currentAnimationId = -1;    //The current animation ID.
    private int nextAnimationId = -1;   // If we are asked to run another animation while an uninterrumpible animation
                                        // is running, the new animation will be stored and will wait for its turn.

    private Animation animation;    //The current Animation, from which we will get the frame that will be drawn.

    private float stateTime = 0f;   //It's a counter that controls the time that each frame has to be shown.

    //originalTexture: The originalTexture region that contains all the frames of the animation.
    //numRows, numCols: Number of rows and columns that the originalTexture has.
    //numFrames: The number of frames for this animation.
    //durationFrame: Duration of each frame.
    protected Nekomata(TextureRegion texture, int numRows, int numCols, int numFramesPerAnimation, float durationFrame, boolean reverse)
    {
        if (texture == null)
        {
            return;
        }

        // Calculate each frame dimensions.
        int frameWidth = texture.getRegionWidth()/ numCols;
        int frameHeight = texture.getRegionHeight()/ numRows;

        //Now we set calculated values as Actor's properties.
        setWidth(frameWidth);
        setHeight(frameHeight);

        //Split original originalTexture into individual textures containing one frame each.
        TextureRegion[][] frames = texture.split(frameWidth, frameHeight);

        int numAnimationsPerRow = numCols / numFramesPerAnimation;
        animations = new Array<>(numAnimationsPerRow * numRows);
        
        int numFrame;

        AnimacionConfig animation = new AnimacionConfig(numFramesPerAnimation, reverse);

        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                // This way I visit each frame of the animation.
                numFrame = j % numFramesPerAnimation;
                animation.frames[numFrame] = frames[i][j];

                //Generate intermediate animations to allow generation of revert effect when needed.
                if (numFrame > 0 && numFrame < numFramesPerAnimation - 1)
                {
                    animation.frames[numFramesPerAnimation - 1 + numFrame] = animation.frames[numFrame];
                }

                //Once we've generate all the frames, we give it a duration time, we add it and we start creating the next one.
                if (numFrame >= numFramesPerAnimation-1)
                {   
                    animation.durationFrame = durationFrame;
                    animations.add(animation);
                    animation = new AnimacionConfig(numFramesPerAnimation, true);
                }
            }
        }

        //Initialize the with default animation:
        setAnimation(0, true);
    }

    //Stores the config params for each animation.
    //These params will be loaded when we load the Animation itself.
    public static class AnimacionConfig
    {
        private final TextureRegion[] frames; //Frames that compose the animation.
        public float durationFrame;     //Duration of each frame.
        public final boolean loop = true;     //Repeat indefinitely.
        public final boolean reverse = true;  // Join the intermediate frames so Animation will revert instead of
        // starting from the beginning when it reaches its last frame.
        public final boolean uninterrumpible = false; // Can't be interrupted by another animation.
        public final boolean dieAfterAnimationCompleted = false;  //Will be disposed after being animated once.
        public final boolean isPaused = false;    //Is it paused?

        public AnimacionConfig(int numFrames, boolean reverse)
        {
            //This constructor will calculate the lenght of the array containing the frames,
            //in order to keep room for the reverse effect.
            if (reverse)
            {
                frames = new TextureRegion[numFrames * 2 - 2];
            }
            else
            {
                frames = new TextureRegion[numFrames];
            }
        }
    }

    private int getNumFramesAnimation()
    {
        return animations.get(currentAnimationId).frames.length;
    }

    // This method loads the animation and all its parameters from the animations array.
    // If we try to load the same animation that it's currently running it won't do anything.
    // If the animation is the one that's currently running but we activate the forceAnimation boolean,
    // the animation won't be loaded again, but it will be restarted.
    // If the current animation is uninterrumpible, the new animation's ID will be stored
    // and be run later.
    protected final void setAnimation(int animationId, boolean forceAnimation)
    {
        if (animationId < 0 || animationId >= animations.size)
        {
            return;
        }

        if(currentAnimationId == -1)
        {
            currentAnimation = animations.get(animationId);
        }
        else
        {
            currentAnimation = animations.get(currentAnimationId);
        }

        //If current animation is uninterrumpible, set new animation as next.
        if (currentAnimation.uninterrumpible && !forceAnimation)
        {
            nextAnimationId = animationId;
            return;
        }

        //Force animation will restart animation (without loading it again)
        if (forceAnimation)
        {
            stateTime = 0f;
        }

        if (currentAnimationId == -1 || animationId != currentAnimationId)
        {
            animation = new Animation(currentAnimation.durationFrame, animations.get(animationId).frames);
            currentAnimationId = animationId;
            stateTime = 0f;
        }
    }

    public abstract void updateAnimation();

    //DRAW:
    // We calculate how much time we need to run the whole animation,
    // and we compare it with the stateTime. This way we can know if the animation has been entirely reproduced.
    // Depending on the stateTime, we get the frame of the animation and we draw it on the screen with modified alpha,
    // and finally we restore the color to restore the alpha to its original value.
    @Override public void draw (Batch batch, float alpha)
    {
        //To modify alpha, we need to first load the color.
        Color oldColor = batch.getColor();
        Color newColor = batch.getColor();
        newColor.a = alpha;
        batch.setColor(newColor);
        
        int numFramesAnimation = getNumFramesAnimation();

        if (!currentAnimation.isPaused)
        {
            stateTime += Gdx.graphics.getDeltaTime();
        }

        if (!currentAnimation.reverse && stateTime > currentAnimation.durationFrame * (numFramesAnimation + 2) / 2)
        {
            stateTime = 0f;
            resetFlags();
        }

        if (currentAnimation.reverse && currentAnimation.durationFrame > currentAnimation.durationFrame * (numFramesAnimation))
        {
            resetFlags();
        }

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, currentAnimation.loop);

        Vector2 Offset = new Vector2(0,0);

        batch.draw(currentFrame,
                getX() + Offset.x,
                getY() + Offset.y,
                getOriginX(),
                getOriginY(),
                getWidth(),
                getHeight(),
                getScaleX(),
                getScaleY(),
                getRotation());
        
        //Restore original color.
        batch.setColor(oldColor);
    }
    
    private void resetFlags()
    {
        if(currentAnimation.dieAfterAnimationCompleted)
        {
            getParent().removeActor(this);
        }

        if(nextAnimationId >= 0)
        {
            setAnimation(nextAnimationId, true);
            nextAnimationId = -1;
        }
    }
}
