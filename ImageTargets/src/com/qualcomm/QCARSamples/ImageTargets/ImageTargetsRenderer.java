/*==============================================================================
            Copyright (c) 2012 QUALCOMM Austria Research Center GmbH.
            All Rights Reserved.
            Qualcomm Confidential and Proprietary
            
@file 
    ImageTargetsRenderer.java

@brief
    Sample for ImageTargets

==============================================================================*/


package com.qualcomm.QCARSamples.ImageTargets;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.qualcomm.QCAR.QCAR;


/** The renderer class for the ImageTargets sample. */
public class ImageTargetsRenderer implements GLSurfaceView.Renderer
{
    public boolean mIsActive = false;
    
    /** Native function for initializing the renderer. */
    public native void initRendering();
    
    
    /** Native function to update the renderer. */
    public native void updateRendering(int width, int height);

    
 public static Handler mainActivityHandler;
    
    // Called from native to display a message
 // Store the activity context
 public static Context context;
 public static int flag=0;
 
 public void onImageTracked(String trackableName)
 {

 	
    Log.d("prabhu","in onImageTracked....."+trackableName);
    Intent intent = new Intent(context,OptionDisplayActivity.class);
    if(trackableName.startsWith("swift"))
    {
		
		intent.putExtra(OptionDisplayActivity.SELECTED_CAR, OptionDisplayActivity.SWIFT);
    }
    else if(trackableName.startsWith("alto"))
    {
    	intent.putExtra(OptionDisplayActivity.SELECTED_CAR, OptionDisplayActivity.ALTO);
    }
    else if(trackableName.startsWith("wagonr"))
    {
    	intent.putExtra(OptionDisplayActivity.SELECTED_CAR, OptionDisplayActivity.WAGONR);
    }
    else if(trackableName.startsWith("ritz"))
    {
    	intent.putExtra(OptionDisplayActivity.SELECTED_CAR, OptionDisplayActivity.RITZ);
		  
    }
    context.startActivity(intent);
}
    
    /** Called when the surface is created or recreated. */
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        DebugLog.LOGD("GLRenderer::onSurfaceCreated");

        // Call native function to initialize rendering:
        initRendering();
        
        // Call QCAR function to (re)initialize rendering after first use
        // or after OpenGL ES context was lost (e.g. after onPause/onResume):
        QCAR.onSurfaceCreated();
    }
    
    
    /** Called when the surface changed size. */
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        DebugLog.LOGD("GLRenderer::onSurfaceChanged");
        
        // Call native function to update rendering when render surface parameters have changed:
        updateRendering(width, height);

        // Call QCAR function to handle render surface size changes:
        QCAR.onSurfaceChanged(width, height);
    }    
    
    
    /** The native render function. */    
    public native void renderFrame();
    
    
    /** Called to draw the current frame. */
    public void onDrawFrame(GL10 gl)
    {
        if (!mIsActive)
            return;

        // Call our native function to render content
        renderFrame();
    }
   
}
