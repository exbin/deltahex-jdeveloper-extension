/*
 * Copyright (C) ExBin Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.exbin.framework.bined.options.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import org.exbin.bined.EditationMode;
import org.exbin.bined.SelectionRange;
import org.exbin.bined.capability.EditationModeCapable;
import org.exbin.bined.capability.RowWrappingCapable;
import org.exbin.bined.highlight.swing.extended.ExtendedHighlightCodeAreaPainter;
import org.exbin.bined.highlight.swing.extended.ExtendedHighlightNonAsciiCodeAreaPainter;
import org.exbin.bined.swing.extended.ExtCodeArea;
import org.exbin.bined.swing.extended.color.ExtendedCodeAreaColorProfile;
import org.exbin.framework.gui.utils.LanguageUtils;
import org.exbin.framework.gui.utils.WindowUtils;
import org.exbin.utils.binary_data.ByteArrayEditableData;

/**
 * Color profile panel.
 *
 * @version 0.2.0 2019/03/12
 * @author ExBin Project (http://exbin.org)
 */
public class ColorProfilePanel extends javax.swing.JPanel {

    private final java.util.ResourceBundle resourceBundle = LanguageUtils.getResourceBundleByClass(ColorProfilePanel.class);

    private final ColorProfileTableModel colorTableModel = new ColorProfileTableModel();

    private ExtCodeArea codeArea;

    public ColorProfilePanel() {
        initComponents();
        init();
    }

    private void init() {
        codeArea = new ExtCodeArea();
        initPreviewCodeArea();
        previewPanel.add(codeArea, BorderLayout.CENTER);

        colorsTable.setDefaultRenderer(Color.class, new ColorCellTableRenderer());
        colorsTable.setDefaultEditor(Color.class, new ColorCellTableEditor());

        colorTableModel.setColorProfile((ExtendedCodeAreaColorProfile) codeArea.getColorsProfile());
    }

    private void initPreviewCodeArea() {
        ((EditationModeCapable) codeArea).setEditationMode(EditationMode.READ_ONLY);
        ExtendedHighlightNonAsciiCodeAreaPainter painter = new ExtendedHighlightNonAsciiCodeAreaPainter(codeArea);
        codeArea.setPainter(painter);
        List<ExtendedHighlightCodeAreaPainter.SearchMatch> exampleMatches = new ArrayList<ExtendedHighlightCodeAreaPainter.SearchMatch>();
        // Set manual search matches for "ligula"
        exampleMatches.add(new ExtendedHighlightCodeAreaPainter.SearchMatch(145, 6));
        exampleMatches.add(new ExtendedHighlightCodeAreaPainter.SearchMatch(480, 6));
        exampleMatches.add(new ExtendedHighlightCodeAreaPainter.SearchMatch(1983, 6));
        painter.setMatches(exampleMatches);
        painter.setCurrentMatchIndex(1);
        ByteArrayEditableData exampleData = new ByteArrayEditableData();
        try {
            exampleData.loadFromStream(getClass().getResourceAsStream("/org/exbin/framework/bined/resources/preview/lorem.txt"));
        } catch (IOException ex) {
            Logger.getLogger(ColorProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        codeArea.setContentData(exampleData);
        ((RowWrappingCapable) codeArea).setRowWrapping(RowWrappingCapable.RowWrappingMode.WRAPPING);
        codeArea.setEnabled(false);
        codeArea.setShowUnprintables(true);
        codeArea.setSelection(new SelectionRange(200, 300));
    }

    public void setColorProfile(@Nonnull ExtendedCodeAreaColorProfile colorProfile) {
        ExtendedCodeAreaColorProfile newColorProfile = colorProfile.createCopy();
        codeArea.setColorsProfile(newColorProfile);
        colorTableModel.setColorProfile(newColorProfile);
    }

    @Nonnull
    public ExtendedCodeAreaColorProfile getColorProfile() {
        ExtendedCodeAreaColorProfile profile = (ExtendedCodeAreaColorProfile) codeArea.getColorsProfile();
        return Objects.requireNonNull(profile).createCopy();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        preferencesPanel = new javax.swing.JPanel();
        colorsScrollPane = new javax.swing.JScrollPane();
        colorsTable = new javax.swing.JTable();
        previewPanel = new javax.swing.JPanel();
        previewLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        preferencesPanel.setLayout(new java.awt.BorderLayout());

        colorsTable.setModel(colorTableModel);
        colorsScrollPane.setViewportView(colorsTable);

        preferencesPanel.add(colorsScrollPane, java.awt.BorderLayout.CENTER);

        add(preferencesPanel, java.awt.BorderLayout.WEST);

        previewPanel.setLayout(new java.awt.BorderLayout());

        previewLabel.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
        previewLabel.setText(resourceBundle.getString("previewLabel.text")); // NOI18N
        previewLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        previewLabel.setOpaque(true);
        previewPanel.add(previewLabel, java.awt.BorderLayout.NORTH);

        add(previewPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Test method for this panel.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        WindowUtils.invokeDialog(new ColorProfilePanel());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane colorsScrollPane;
    private javax.swing.JTable colorsTable;
    private javax.swing.JPanel preferencesPanel;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JPanel previewPanel;
    // End of variables declaration//GEN-END:variables
}
