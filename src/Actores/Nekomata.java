package Actores;

import ch.qos.logback.classic.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import org.slf4j.LoggerFactory;

public class Nekomata extends Actor
{
    private Array<AnimacionConfig> animations = new Array<>();
    private AnimacionConfig currentAnimation;
    private int currentAnimationId = -1;
    private int nextAnimationId = -1;
    private Animation animation;
    private TextureRegion originalTexture;
    private TextureRegion currentFrame;              //Frame actual que se dibujara
    private int numRows;
    private int numCols;
    private float stateTime = 0f;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    public static class AnimacionConfig
    { 
        private int id;
        private TextureRegion[] frames; //Frames that compose the animation.
        public float durationFrame;     //Duration of each frame.
        public boolean loop = true;
        public boolean reverse = true;
        public boolean uninterrumpible = false; // Can't be interrupted by another animation.
        public boolean dieAfterAnimationCompleted = false;
        public boolean isPaused = false;

        public AnimacionConfig(int numFrames, boolean reverse)
        {
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

    public int getNumFramesAnimation()
    {
        return animations.get(currentAnimation.id).frames.length;
    }
    
    //originalTexture: The originalTexture region that contains all the frames of the animation.
    //numRows, numCols: Number of rows and columns that the originalTexture has.
    //numFrames: The number of frames for this animation.
    //durationFrame: Duration of each frame.
    public Nekomata(TextureRegion texture, int numRows, int numCols, int numFramesPerAnimation, float durationFrame)
    {
        if (texture == null)
        {
            logger.error("The originalTexture does not exist.");
            return;
        }

        this.originalTexture = texture;
        this.numRows = numRows;
        this.numCols = numCols;

        // Calculate each frame dimensions.
        int frameWidth = this.originalTexture.getRegionWidth()/this.numCols;
        int frameHeight = this.originalTexture.getRegionHeight()/this.numRows;

        //Now we set calculated values as Actor's properties.
        this.setWidth(frameWidth);
        this.setHeight(frameHeight);

        //Split original originalTexture into individual textures containing one frame each.
        TextureRegion[][] frames = this.originalTexture.split(frameWidth, frameHeight);

        int numAnimationsPerRow = this.numCols/numFramesPerAnimation;
        animations = new Array<>(numAnimationsPerRow*this.numRows);
        
        int numFrame;

        AnimacionConfig animation = new AnimacionConfig(numFramesPerAnimation, true);

        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < this.numCols; j++)
            {
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
        setAnimacion(0, true);
    }

    public final void setAnimacion (int animationId, boolean forceAnimation)
    {
        if (animationId < 0 || animationId >= animations.size)
        {
            logger.error("The animation does not exist.");
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

        //Force animation will reboot animation (without loading it again)
        if (forceAnimation)
        {
            stateTime = 0f;
        }

        if (currentAnimationId == -1 || animationId != this.currentAnimation.id)
        {
            animation = new Animation(currentAnimation.durationFrame, animations.get(animationId).frames);
            currentAnimationId = animationId;
            stateTime = 0f;
        }
    }

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

        currentFrame = animation.getKeyFrame(stateTime, currentAnimation.loop);

        Vector2 Offset = new Vector2(0,0);

        batch.draw(currentFrame,
                getX() + Offset.x,
                getY() + Offset.y,
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation());
        
        //Restore original color.
        batch.setColor(oldColor);
    }
    
    public void resetFlags()
    {
        if(currentAnimation.dieAfterAnimationCompleted)
        {
            this.getParent().removeActor(this);
        }

        if(nextAnimationId >= 0)
        {
            setAnimacion(nextAnimationId, true);
            nextAnimationId = -1;
        }
    }
}
