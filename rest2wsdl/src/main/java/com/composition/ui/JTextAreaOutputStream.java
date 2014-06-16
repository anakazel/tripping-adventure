package com.composition.ui;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by alexg on 16.06.2014.
 * Used to redirect the System out stream to the text area
 */
public class JTextAreaOutputStream extends OutputStream
{
    private final JTextArea destination;

    public JTextAreaOutputStream (JTextArea destination)
    {
        if (destination == null)
            throw new IllegalArgumentException ("Destination is null");

        this.destination = destination;
    }

    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException
    {
        final String text = new String (buffer, offset, length);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                destination.append(text);
            }
        });
    }

    @Override
    public void write(int b) throws IOException
    {
        write (new byte [] {(byte)b}, 0, 1);
    }
}
