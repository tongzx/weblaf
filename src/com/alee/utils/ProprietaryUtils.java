/*
* This file is part of WebLookAndFeel library.
*
* WebLookAndFeel library is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* WebLookAndFeel library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.utils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * This utility class operates only with proprietary API calls.
 * Their usage is inevitable, otherwise i would have replaced them with something else.
 *
 * @author Mikle Garin
 */

public class ProprietaryUtils
{
    /**
     * Installs some proprietary L&F defaults for proper text rendering.
     * <p/>
     * Basically this method is a workaround for this simple call:
     * <code>
     * table.put ( sun.swing.SwingUtilities2.AA_TEXT_PROPERTY_KEY, sun.swing.SwingUtilities2.AATextInfo.getAATextInfo ( true ) );
     * </code>
     * but it doesn't directly use any proprietary API.
     *
     * @param table defaults table
     */
    public static void setupDefaults ( UIDefaults table )
    {
        try
        {
            Class su2 = ReflectUtils.getClass ( "sun.swing.SwingUtilities2" );
            Object aaProperty = ReflectUtils.getStaticFieldValue ( su2, "AA_TEXT_PROPERTY_KEY" );
            Class aaTextInfo = ReflectUtils.getInnerClass ( su2, "AATextInfo" );
            Object aaValue = ReflectUtils.callStaticMethod ( aaTextInfo, "getAATextInfo", true );
            table.put ( aaProperty, aaValue );
        }
        catch ( ClassNotFoundException e )
        {
            e.printStackTrace ();
        }
        catch ( NoSuchFieldException e )
        {
            e.printStackTrace ();
        }
        catch ( IllegalAccessException e )
        {
            e.printStackTrace ();
        }
        catch ( NoSuchMethodException e )
        {
            e.printStackTrace ();
        }
        catch ( InvocationTargetException e )
        {
            e.printStackTrace ();
        }
    }

    /**
     * Sets window opaque if that option is supported by the underlying system.
     *
     * @param window window to process
     * @param opaque whether should make window opaque or not
     */
    public static void setWindowOpaque ( Window window, boolean opaque )
    {
        if ( window != null && isWindowTransparencyAllowed () )
        {
            try
            {
                // Workaround to allow this method usage on all possible Java versions
                ReflectUtils.callStaticMethod ( "com.sun.awt.AWTUtilities", "setWindowOpaque", window, opaque );
            }
            catch ( Throwable e )
            {
                // Ignore any exceptions this native feature might cause
                // Still, should inform that such actions cause an exception on the underlying system
                e.printStackTrace ();
            }
        }
    }

    /**
     * Returns whether window is opaque or not.
     *
     * @param window window to process
     * @return whether window background is opaque or not
     */
    public static boolean isWindowOpaque ( Window window )
    {
        if ( window != null && isWindowTransparencyAllowed () )
        {
            try
            {
                // Workaround to allow this method usage on all possible Java versions
                return ( Boolean ) ReflectUtils.callStaticMethod ( "com.sun.awt.AWTUtilities", "isWindowOpaque", window );
            }
            catch ( Throwable e )
            {
                // Ignore any exceptions this native feature might cause
                // Still, should inform that such actions cause an exception on the underlying system
                e.printStackTrace ();
            }
        }
        return true;
    }

    /**
     * Sets window opacity if that option is supported by the underlying system.
     *
     * @param window  window to process
     * @param opacity new window opacity
     */
    public static void setWindowOpacity ( Window window, float opacity )
    {
        if ( window != null && isWindowTransparencyAllowed () )
        {
            try
            {
                // Workaround to allow this method usage on all possible Java versions
                ReflectUtils.callStaticMethod ( "com.sun.awt.AWTUtilities", "setWindowOpacity", window, opacity );
            }
            catch ( Throwable e )
            {
                // Ignore any exceptions this native feature might cause
                // Still, should inform that such actions cause an exception on the underlying system
                e.printStackTrace ();
            }
        }
    }

    /**
     * Returns window opacity.
     *
     * @param window window to process
     * @return window opacity
     */
    public static float getWindowOpacity ( Window window )
    {
        if ( window != null && isWindowTransparencyAllowed () )
        {
            try
            {
                // Workaround to allow this method usage on all possible Java versions
                return ( Float ) ReflectUtils.callStaticMethod ( "com.sun.awt.AWTUtilities", "getWindowOpacity", window );
            }
            catch ( Throwable e )
            {
                // Ignore any exceptions this native feature might cause
                // Still, should inform that such actions cause an exception on the underlying system
                e.printStackTrace ();
            }
        }
        return 1f;
    }

    /**
     * Returns whether window transparency is supported on current OS or not.
     *
     * @return true if window transparency is supported on current OS; false otherwise
     */
    public static boolean isWindowTransparencyAllowed ()
    {
        try
        {
            // todo Replace when Linux will have proper support for transparency
            // com.sun.awt.AWTUtilities.isTranslucencySupported ( com.sun.awt.AWTUtilities.Translucency.PERPIXEL_TRANSPARENT )
            return SystemUtils.isWindows () || SystemUtils.isMac () || SystemUtils.isSolaris ();
        }
        catch ( Throwable e )
        {
            return false;
        }
    }
}