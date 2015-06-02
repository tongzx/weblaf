package com.alee.laf.text;

import com.alee.extended.painter.AdaptivePainter;
import com.alee.extended.painter.Painter;

import javax.swing.*;

/**
 * Simple TextPanePainter adapter class.
 * It is used to install simple non-specific painters into WebTextPaneUI.
 *
 * @author Alexandr Zernov
 */

public class AdaptiveTextPanePainter<E extends JTextPane, U extends WebTextPaneUI> extends AdaptivePainter<E, U>
        implements TextPanePainter<E, U>
{
    /**
     * Constructs new AdaptiveTextPanePainter for the specified painter.
     *
     * @param painter painter to adapt
     */
    public AdaptiveTextPanePainter ( final Painter painter )
    {
        super ( painter );
    }
}