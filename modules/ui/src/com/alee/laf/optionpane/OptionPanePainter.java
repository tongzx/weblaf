package com.alee.laf.optionpane;

import com.alee.extended.painter.Painter;
import com.alee.extended.painter.SpecificPainter;

import javax.swing.*;

/**
 * Base interface for JOptionPane component painters.
 *
 * @author Alexandr Zernov
 */

public interface OptionPanePainter<E extends JOptionPane, U extends WebOptionPaneUI> extends Painter<E, U>, SpecificPainter
{
}