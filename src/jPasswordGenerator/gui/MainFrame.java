/*      */ package jPasswordGenerator.gui;
/*      */ 
/*      */ import jPasswordGenerator.Generator;
/*      */ import java.awt.Font;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.io.File;
/*      */ import java.util.Properties;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JSeparator;
/*      */ import javax.swing.JTextArea;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
/*      */ 
/*      */ public class MainFrame extends javax.swing.JFrame
/*      */ {
/*   32 */   public static String guiVersion = "4.55.00";
/*   33 */   private javax.swing.InputVerifier integerVerifier = new javax.swing.InputVerifier() {
/*      */     public boolean verify(javax.swing.JComponent input) {
/*   35 */       JTextField tf = (JTextField)input;
/*   36 */       String outStr = "";
/*   37 */       String x = tf.getText();
/*   38 */       for (int i = 0; i < x.length(); i++) {
/*   39 */         if (Generator.numerCharSet.contains("" + x.charAt(i))) {
/*   40 */           outStr = outStr + x.charAt(i);
/*      */         }
/*      */       }
/*   43 */       tf.setText(outStr);
/*   44 */       return true;
/*      */     }
/*      */   };
/*      */   
/*      */   private Generator gen;
/*   49 */   private java.util.concurrent.ExecutorService threadExecutor = java.util.concurrent.Executors.newFixedThreadPool(1);
/*   50 */   private boolean isSaved = false;
/*      */   private File saveToFile;
/*   52 */   private String previousText = "";
/*   53 */   private Properties options = new Properties();
/*      */   public JButton about_Button;
/*      */   public JCheckBox allowDuplicates;
/*      */   
/*   57 */   public MainFrame() { this.gen = new Generator() {
/*      */       public void onProgressUpdate() {
/*   59 */         MainFrame.this.updateStatus();
/*      */       }
/*      */       
/*      */       public void onComplete() {
/*   63 */         MainFrame.this.updateStatusComplete();
/*      */       }
/*   65 */     };
/*   66 */     initMainFrame();
/*      */   }
/*      */   
/*      */   public JComboBox charSet;
/*      */   public JButton close_Button;
/*      */   public JTextArea editorArea;
/*      */   public JScrollPane editorPane;
/*      */   
/*   74 */   public MainFrame(Properties newProps) { this.gen = new Generator() {
/*      */       public void onProgressUpdate() {
/*   76 */         MainFrame.this.updateStatus();
/*      */       }
/*      */       
/*      */       public void onComplete() {
/*   80 */         MainFrame.this.updateStatusComplete();
/*      */       }
/*   82 */     };
/*   83 */     this.options = newProps;
/*   84 */     initMainFrame();
/*      */   }
/*      */   
/*      */   public JPanel editorPanel;
/*      */   public JTextPane excludeChars;
/*      */   public JScrollPane excludeChars_Scroll;
/*      */   public JTextPane explicitChars;
/*      */   public MainFrame(Generator newGen) {
/*   92 */     this.gen = newGen;
/*   93 */     initMainFrame();
/*      */   }
/*      */   
/*      */   public JScrollPane explicitChars_Scroll;
/*      */   public JTextField formatPwords;
/*      */   public JPanel formattingPanel;
/*      */   public JTextPane formatting_Help_pane;
/*      */   public JScrollPane formatting_Help_scroll;
/*      */   
/*  102 */   public MainFrame(Generator newGen, Properties newProps) { this.gen = newGen;
/*  103 */     initMainFrame();
/*  104 */     this.options = newProps; }
/*      */   
/*      */   public JButton generate_Button;
/*      */   public JTextPane includeChars;
/*      */   public JScrollPane includeChars_Scroll;
/*      */   public JButton jButton1;
/*      */   public JLabel jLabel1;
/*  111 */   public void initMainFrame() { try { javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
/*      */     }
/*      */     catch (Exception e) {
/*      */       try {
/*  115 */         javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
/*      */       } catch (Exception ex) {
/*  117 */         System.exit(1);
/*      */       }
/*      */     }
/*  120 */     echo("Using " + javax.swing.UIManager.getLookAndFeel().getName() + " for the Look and Feel.");
/*  121 */     initComponents();
/*  122 */     //setExtendedState(6);
               this.licence_Button.setVisible(false);
               this.about_Button.setVisible(false);
               this.charSet.setSelectedIndex(4);
/*      */     
/*  124 */     charSetItemStateChanged(null);
/*  125 */     this.numberPwords.setInputVerifier(this.integerVerifier);
/*  126 */     this.lengthPwords.setInputVerifier(this.integerVerifier);
/*      */   }
/*      */   
/*      */   public JLabel jLabel2;
/*      */   public JSeparator jSeparator1;
/*      */   public JSeparator jSeparator2;
/*      */   public JSeparator jSeparator3;
/*      */   public JSeparator jSeparator4;
/*      */   
/*      */   private void initComponents() {
/*  136 */     this.splitPane = new javax.swing.JSplitPane();
/*  137 */     this.editorPanel = new JPanel();
/*  138 */     this.editorPane = new JScrollPane();
/*  139 */     this.editorArea = new JTextArea();
/*  140 */     this.optionsScrollPane = new JScrollPane();
/*  141 */     this.optionsPanel = new JPanel();
/*  142 */     this.charSet = new JComboBox();
/*  143 */     this.jSeparator1 = new JSeparator();
/*  144 */     this.explicitChars_Scroll = new JScrollPane();
/*  145 */     this.explicitChars = new JTextPane();
/*  146 */     this.includeChars_Scroll = new JScrollPane();
/*  147 */     this.includeChars = new JTextPane();
/*  148 */     this.excludeChars_Scroll = new JScrollPane();
/*  149 */     this.excludeChars = new JTextPane();
/*  150 */     this.jSeparator2 = new JSeparator();
/*  151 */     this.pwordOptionsPanel = new JPanel();
/*  152 */     this.numberPwords_Label = new JLabel();
/*  153 */     this.numberPwords = new JTextField();
/*  154 */     this.lengthPwords_Label = new JLabel();
/*  155 */     this.lengthPwords = new JTextField();
/*  156 */     this.allowDuplicates = new JCheckBox();
/*  157 */     this.useWords_checkbox = new JCheckBox();
/*  158 */     this.jLabel1 = new JLabel();
/*  159 */     this.jLabel2 = new JLabel();
/*  160 */     this.minWordLen_box = new JTextField();
/*  161 */     this.maxWordLen_box = new JTextField();
/*  162 */     this.jSeparator3 = new JSeparator();
/*  163 */     this.formattingPanel = new JPanel();
/*  164 */     this.formatPwords = new JTextField();
/*  165 */     this.formatting_Help_scroll = new JScrollPane();
/*  166 */     this.formatting_Help_pane = new JTextPane();
/*  167 */     this.jSeparator4 = new JSeparator();
/*  168 */     this.saveSettingsButton = new JButton();
/*  169 */     this.loadSettingsButton = new JButton();
/*  170 */     this.generate_Button = new JButton();
/*  171 */     this.save_Button = new JButton();
/*  172 */     this.close_Button = new JButton();
/*  173 */     this.about_Button = new JButton();
/*  174 */     this.licence_Button = new javax.swing.JToggleButton();
/*  175 */     this.progressBar = new JProgressBar();
/*  176 */     this.jButton1 = new JButton();
/*      */     
/*  178 */     setDefaultCloseOperation(3);
/*  179 */     setTitle("jPasswordGen");
/*      */     
/*  181 */     this.splitPane.setBorder(null);
/*  182 */     this.splitPane.setDividerLocation(251);
/*  183 */     this.splitPane.setDividerSize(0);
/*      */     
/*  185 */     this.editorPanel.setMinimumSize(new java.awt.Dimension(250, 0));
/*      */     
/*  187 */     this.editorPane.setVerticalScrollBarPolicy(22);
/*      */     
/*  189 */     this.editorArea.setColumns(20);
/*  190 */     this.editorArea.setFont(new Font("Courier New", 0, 12));
/*  191 */     this.editorArea.setRows(5);
/*  192 */     this.editorPane.setViewportView(this.editorArea);
/*      */     
/*  194 */     GroupLayout editorPanelLayout = new GroupLayout(this.editorPanel);
/*  195 */     this.editorPanel.setLayout(editorPanelLayout);
/*  196 */     editorPanelLayout.setHorizontalGroup(editorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.editorPane, GroupLayout.Alignment.TRAILING, -1, 752, 32767));
/*      */     
/*      */ 
/*      */ 
/*  200 */     editorPanelLayout.setVerticalGroup(editorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.editorPane, -1, 410, 32767));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  205 */     this.splitPane.setRightComponent(this.editorPanel);
/*      */     
/*  207 */     this.optionsScrollPane.setBorder(null);
/*  208 */     this.optionsScrollPane.setMinimumSize(new java.awt.Dimension(252, 22));
/*  209 */     this.optionsScrollPane.setPreferredSize(new java.awt.Dimension(275, 750));
/*      */     
/*  211 */     this.optionsPanel.setMaximumSize(new java.awt.Dimension(250, 32767));
/*  212 */     this.optionsPanel.setMinimumSize(new java.awt.Dimension(233, 0));
/*  213 */     this.optionsPanel.setPreferredSize(new java.awt.Dimension(233, 700));
/*      */     
/*  215 */     this.charSet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alpha-Numeric Only", "Alpha-Numeric Easy to Read", "Alpha-Numeric and Symbols", "Symbols Only", "User Defined - All Random", "User Defined - Formatted" }));
/*  216 */     this.charSet.setBorder(BorderFactory.createTitledBorder("Character Set"));
/*  217 */     this.charSet.addItemListener(new java.awt.event.ItemListener() {
/*      */       public void itemStateChanged(java.awt.event.ItemEvent evt) {
/*  219 */         MainFrame.this.charSetItemStateChanged(evt);
/*      */       }
/*      */       
/*  222 */     });
/*  223 */     this.explicitChars_Scroll.setBorder(BorderFactory.createTitledBorder("Explicit Characters"));
/*  224 */     this.explicitChars_Scroll.setViewportBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
/*      */     
/*  226 */     this.explicitChars.setFont(new Font("Courier New", 0, 12));
/*  227 */     this.explicitChars_Scroll.setViewportView(this.explicitChars);
/*      */     
/*  229 */     this.includeChars_Scroll.setBorder(BorderFactory.createTitledBorder("Include Characters"));
/*  230 */     this.includeChars_Scroll.setViewportBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
/*      */     
/*  232 */     this.includeChars.setFont(new Font("Courier New", 0, 12));
/*  233 */     this.includeChars_Scroll.setViewportView(this.includeChars);
/*      */     
/*  235 */     this.excludeChars_Scroll.setBorder(BorderFactory.createTitledBorder("Exclude Characters"));
/*  236 */     this.excludeChars_Scroll.setViewportBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
/*      */     
/*  238 */     this.excludeChars.setFont(new Font("Courier New", 0, 12));
/*  239 */     this.excludeChars_Scroll.setViewportView(this.excludeChars);
/*      */     
/*  241 */     this.pwordOptionsPanel.setBorder(BorderFactory.createTitledBorder("Password Options"));
/*      */     
/*  243 */     this.numberPwords_Label.setLabelFor(this.numberPwords);
/*  244 */     this.numberPwords_Label.setText("Number of Passwords");
/*      */     
/*  246 */     this.numberPwords.setFont(new Font("DejaVu Sans", 0, 12));
/*  247 */     this.numberPwords.setText("10");
/*  248 */     this.numberPwords.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
/*      */     
/*  250 */     this.lengthPwords_Label.setLabelFor(this.lengthPwords);
/*  251 */     this.lengthPwords_Label.setText("Length of Passwords");
/*      */     
/*  253 */     this.lengthPwords.setFont(new Font("DejaVu Sans", 0, 12));
/*  254 */     this.lengthPwords.setText("16");
/*  255 */     this.lengthPwords.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
/*      */     
/*  257 */     this.allowDuplicates.setText("Allow Duplicates");
/*  258 */     this.allowDuplicates.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  259 */     this.allowDuplicates.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  261 */         MainFrame.this.allowDuplicatesMouseClicked(evt);
/*      */       }
/*      */       
/*  264 */     });
/*  265 */     this.useWords_checkbox.setText("Use Words");
/*  266 */     this.useWords_checkbox.addActionListener(new java.awt.event.ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  268 */         MainFrame.this.useWords_checkboxActionPerformed(evt);
/*      */       }
/*      */       
/*  271 */     });
/*  272 */     this.jLabel1.setText("Minimum Word Length");
/*      */     
/*  274 */     this.jLabel2.setText("Maximum Word Length");
/*      */     
/*  276 */     this.minWordLen_box.setFont(new Font("DejaVu Sans", 0, 12));
/*  277 */     this.minWordLen_box.setText("6");
/*      */     
/*  279 */     this.maxWordLen_box.setFont(new Font("DejaVu Sans", 0, 12));
/*  280 */     this.maxWordLen_box.setText("8");
/*      */     
/*  282 */     GroupLayout pwordOptionsPanelLayout = new GroupLayout(this.pwordOptionsPanel);
/*  283 */     this.pwordOptionsPanel.setLayout(pwordOptionsPanelLayout);
/*  284 */     pwordOptionsPanelLayout.setHorizontalGroup(pwordOptionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addGroup(pwordOptionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.numberPwords_Label, -1, 173, 32767)).addGroup(GroupLayout.Alignment.TRAILING, pwordOptionsPanelLayout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.numberPwords, -1, 161, 32767)).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.lengthPwords_Label, -1, 173, 32767)).addGroup(GroupLayout.Alignment.TRAILING, pwordOptionsPanelLayout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.lengthPwords, -1, 161, 32767)).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.allowDuplicates, -1, 173, 32767)).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.useWords_checkbox)).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addContainerGap().addGroup(pwordOptionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addGap(12, 12, 12).addComponent(this.minWordLen_box, -1, 161, 32767)).addComponent(this.jLabel1))).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2)).addGroup(GroupLayout.Alignment.TRAILING, pwordOptionsPanelLayout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.maxWordLen_box, -1, 161, 32767))).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  321 */     pwordOptionsPanelLayout.setVerticalGroup(pwordOptionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pwordOptionsPanelLayout.createSequentialGroup().addComponent(this.numberPwords_Label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.numberPwords, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.lengthPwords_Label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.lengthPwords, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.allowDuplicates).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.useWords_checkbox).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.minWordLen_box, -2, 18, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.maxWordLen_box, -2, 18, -2).addContainerGap(-1, 32767)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  346 */     this.formattingPanel.setBorder(BorderFactory.createTitledBorder("Formatting"));
/*      */     
/*  348 */     this.formatPwords.setFont(new Font("Courier New", 0, 12));
/*  349 */     this.formatPwords.setText("********");
/*  350 */     this.formatPwords.addActionListener(new java.awt.event.ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  352 */         MainFrame.this.formatPwordsActionPerformed(evt);
/*      */       }
/*      */       
/*  355 */     });
/*  356 */     this.formatting_Help_pane.setEditable(false);
/*  357 */     this.formatting_Help_pane.setFont(new Font("Dialog", 0, 10));
/*  358 */     this.formatting_Help_pane.setText("Formatting Guide:\nThis guide will help to show you how to use the formatting feature.\nYou insert a specific character (see table below) that represents a randomly selected character from one of the boxes above.\n\nHere is a list of the characters and their meaning.\n\"@\"=Alpha Character\n\"#\"=Numeric Character\n\"%\"=Symbol Character\n\"*\"=Any of these\n\nIf you want to add a specific character that is displayed in all generated passwords, simply put a \"\\\" in front of it.\nFor instance, lets say I want to randomly generate a list of phone numbers.  I would put \"###\\-###\\-####\" in the formatting box above.");
/*  359 */     this.formatting_Help_scroll.setViewportView(this.formatting_Help_pane);
/*      */     
/*  361 */     GroupLayout formattingPanelLayout = new GroupLayout(this.formattingPanel);
/*  362 */     this.formattingPanel.setLayout(formattingPanelLayout);
/*  363 */     formattingPanelLayout.setHorizontalGroup(formattingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, formattingPanelLayout.createSequentialGroup().addContainerGap().addGroup(formattingPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.formatting_Help_scroll, GroupLayout.Alignment.LEADING, -1, 173, 32767).addComponent(this.formatPwords, GroupLayout.Alignment.LEADING, -1, 173, 32767)).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  372 */     formattingPanelLayout.setVerticalGroup(formattingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(formattingPanelLayout.createSequentialGroup().addComponent(this.formatPwords, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.formatting_Help_scroll, -2, 30, 32767).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  381 */     this.saveSettingsButton.setText("Save Settings");
/*  382 */     this.saveSettingsButton.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  384 */         MainFrame.this.saveSettingsButtonMouseClicked(evt);
/*      */       }
/*      */       
/*  387 */     });
/*  388 */     this.loadSettingsButton.setText("Load Settings");
/*  389 */     this.loadSettingsButton.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  391 */         MainFrame.this.loadSettingsButtonMouseClicked(evt);
/*      */       }
/*      */       
/*  394 */     });
/*  395 */     GroupLayout optionsPanelLayout = new GroupLayout(this.optionsPanel);
/*  396 */     this.optionsPanel.setLayout(optionsPanelLayout);
/*  397 */     optionsPanelLayout.setHorizontalGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jSeparator1, GroupLayout.Alignment.TRAILING, -1, 233, 32767).addComponent(this.jSeparator2, -1, 233, 32767).addComponent(this.jSeparator3, -1, 233, 32767).addGroup(optionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.loadSettingsButton, -1, 209, 32767).addContainerGap()).addGroup(optionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.saveSettingsButton, -1, 209, 32767).addContainerGap()).addGroup(optionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.formattingPanel, -1, -1, 32767).addContainerGap()).addComponent(this.jSeparator4, -1, 233, 32767).addGroup(optionsPanelLayout.createSequentialGroup().addContainerGap().addGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.charSet, GroupLayout.Alignment.TRAILING, 0, 209, 32767).addComponent(this.excludeChars_Scroll, GroupLayout.Alignment.TRAILING, -1, 209, 32767).addComponent(this.includeChars_Scroll, GroupLayout.Alignment.TRAILING, -1, 209, 32767).addComponent(this.explicitChars_Scroll, GroupLayout.Alignment.TRAILING, -1, 209, 32767)).addContainerGap()).addGroup(optionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.pwordOptionsPanel, -1, -1, 32767).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  428 */     optionsPanelLayout.setVerticalGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(optionsPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.charSet, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator1, -2, 2, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.explicitChars_Scroll, -2, 81, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.includeChars_Scroll, -2, 81, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.excludeChars_Scroll, -2, 81, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pwordOptionsPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.formattingPanel, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator4, -2, 2, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.saveSettingsButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.loadSettingsButton).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  458 */     this.optionsScrollPane.setViewportView(this.optionsPanel);
/*      */     
/*  460 */     this.splitPane.setLeftComponent(this.optionsScrollPane);
/*      */     
/*  462 */     this.generate_Button.setText("Generate");
/*  463 */     this.generate_Button.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  465 */         MainFrame.this.generate_ButtonMouseClicked(evt);
/*      */       }
/*      */       
/*  468 */     });
/*  469 */     this.save_Button.setText("Save");
/*  470 */     this.save_Button.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  472 */         MainFrame.this.save_ButtonMouseClicked(evt);
/*      */       }
/*      */       
/*  475 */     });
/*  476 */     this.close_Button.setText("Close");
/*  477 */     this.close_Button.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  479 */         MainFrame.this.close_ButtonMouseClicked(evt);
/*      */       }
/*      */       
/*  482 */     });
/*  483 */     this.about_Button.setText("About");
/*  484 */     this.about_Button.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  486 */         MainFrame.this.about_ButtonMouseClicked(evt);
/*      */       }
/*      */       
/*  489 */     });
/*  490 */     this.licence_Button.setText("Licence Agreement");
/*  491 */     this.licence_Button.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  493 */         MainFrame.this.licence_ButtonMouseClicked(evt);
/*      */       }
/*      */       
/*  496 */     });
/*  497 */     this.progressBar.setString("Finished Loading!");
/*  498 */     this.progressBar.setStringPainted(true);
/*      */     
/*  500 */     this.jButton1.setText("Clear");
/*  501 */     this.jButton1.addActionListener(new java.awt.event.ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  503 */         MainFrame.this.jButton1ActionPerformed(evt);
/*      */       }
/*      */       
/*  506 */     });
/*  507 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  508 */     getContentPane().setLayout(layout);
/*  509 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.about_Button).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.licence_Button).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.generate_Button).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.progressBar, -1, 471, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.close_Button).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.save_Button).addContainerGap()).addComponent(this.splitPane, GroupLayout.Alignment.TRAILING, -1, 1003, 32767));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  529 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.splitPane, -1, 410, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.progressBar, -2, 28, -2).addComponent(this.generate_Button).addComponent(this.licence_Button).addComponent(this.about_Button).addComponent(this.jButton1).addComponent(this.close_Button).addComponent(this.save_Button)).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  545 */     pack();
/*      */   }
/*      */   
/*      */   public JTextField lengthPwords;
/*      */   public JLabel lengthPwords_Label;
/*      */   public javax.swing.JToggleButton licence_Button;
/*      */   public JButton loadSettingsButton;
/*      */   public JTextField maxWordLen_box;
/*  553 */   private void loadSettingsButtonMouseClicked(MouseEvent evt) { if (!this.gen.running)
/*      */     {
/*      */       try
/*      */       {
/*  557 */         String RetTxt = this.editorArea.getText().toString();
/*      */         
/*      */ 
/*  560 */         JFileChooser chooser = new JFileChooser();
/*  561 */         chooser.setMultiSelectionEnabled(false);
/*      */         
/*  563 */         if (this.saveToFile != null) {
/*  564 */           chooser.setSelectedFile(this.saveToFile);
/*      */         }
/*      */         
/*      */ 
/*  568 */         chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
/*      */           public boolean accept(File f) {
/*  570 */             return f.getName().endsWith(".cfg");
/*      */           }
/*      */           
/*      */           public String getDescription() {
/*  574 */             return "Configuration Files";
/*      */           }
/*  576 */         });
/*  577 */         chooser.setAcceptAllFileFilterUsed(false);
/*  578 */         int returnVal = chooser.showOpenDialog(this);
/*      */         
/*      */ 
/*  581 */         if (returnVal == 0) {
/*  582 */           this.saveToFile = chooser.getSelectedFile();
/*  583 */           java.io.InputStream inStream = new java.io.FileInputStream(this.saveToFile.getAbsoluteFile());
/*  584 */           this.options.loadFromXML(inStream);
/*  585 */           parseFromOptions();
/*      */         }
/*      */       } catch (java.io.IOException e) {
/*  588 */         System.out.println(e);
/*      */       } catch (NullPointerException n) {
/*  590 */         System.out.println(n);
/*      */       } catch (Exception l) {
/*  592 */         System.out.println(l);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void parseFromOptions()
/*      */   {
/*  600 */     this.charSet.setSelectedIndex(Integer.parseInt(this.options.getProperty("gui.charSet.getSelectedItem", "" + this.charSet.getSelectedIndex())));
/*  601 */     this.includeChars.setText(this.options.getProperty("gui.includeChars.getText", this.includeChars.getText()));
/*  602 */     this.includeChars.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.includeChars.isEnabled", "" + this.includeChars.isEnabled())));
/*  603 */     this.excludeChars.setText(this.options.getProperty("gui.excludeChars.getText", this.excludeChars.getText()));
/*  604 */     this.excludeChars.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.excludeChars.isEnabled", "" + this.excludeChars.isEnabled())));
/*  605 */     this.explicitChars.setText(this.options.getProperty("gui.explicitChars.getText", this.explicitChars.getText()));
/*  606 */     this.explicitChars.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.explicitChars.isEnabled", "" + this.explicitChars.isEnabled())));
/*  607 */     this.numberPwords.setText(this.options.getProperty("gui.numberPwords.getText", this.numberPwords.getText()));
/*  608 */     this.lengthPwords.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.lengthPwords.getText", this.lengthPwords.getText())));
/*  609 */     this.lengthPwords.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.lengthPwords.isEnabled", "" + this.lengthPwords.isEnabled())));
/*  610 */     this.allowDuplicates.setSelected(Boolean.parseBoolean(this.options.getProperty("gui.allowDuplicates.isSelected", "" + this.allowDuplicates.isSelected())));
/*  611 */     this.formattingPanel.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.formattingPanel.isEnabled", "" + this.formattingPanel.isEnabled())));
/*  612 */     this.formatPwords.setText(this.options.getProperty("gui.formatPwords.getText", "" + this.formatPwords.getText()));
/*  613 */     this.formatPwords.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.formatPwords.isEnabled", "" + this.formatPwords.isEnabled())));
/*  614 */     this.formatting_Help_pane.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.formatting_Help_pane.isEnabled", "" + this.formatting_Help_pane.isEnabled())));
/*  615 */     this.formatting_Help_scroll.setEnabled(Boolean.parseBoolean(this.options.getProperty("gui.formatting_Help_scroll.isEnabled", "" + this.formatting_Help_scroll.isEnabled())));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void saveSettingsButtonMouseClicked(MouseEvent evt)
/*      */   {
/*  623 */     this.gen.writeAllProperties();
/*  624 */     this.options = this.gen.genOptions;
/*  625 */     this.options.setProperty("gui.version", guiVersion);
/*  626 */     this.options.setProperty("gui.charSet.getSelectedItem", "" + this.charSet.getSelectedIndex());
/*  627 */     this.options.setProperty("gui.includeChars.getText", this.includeChars.getText());
/*  628 */     this.options.setProperty("gui.includeChars.isEnabled", "" + this.includeChars.isEnabled());
/*  629 */     this.options.setProperty("gui.excludeChars.getText", this.excludeChars.getText());
/*  630 */     this.options.setProperty("gui.excludeChars.isEnabled", "" + this.excludeChars.isEnabled());
/*  631 */     this.options.setProperty("gui.explicitChars.getText", this.explicitChars.getText());
/*  632 */     this.options.setProperty("gui.explicitChars.isEnabled", "" + this.explicitChars.isEnabled());
/*  633 */     this.options.setProperty("gui.numberPwords.getText", this.numberPwords.getText());
/*  634 */     this.options.setProperty("gui.lengthPwords.getText", this.lengthPwords.getText());
/*  635 */     this.options.setProperty("gui.lengthPwords.isEnabled", "" + this.lengthPwords.isEnabled());
/*  636 */     this.options.setProperty("gui.allowDuplicates.isSelected", "" + this.allowDuplicates.isSelected());
/*  637 */     this.options.setProperty("gui.formattingPanel.isEnabled", "" + this.formattingPanel.isEnabled());
/*  638 */     this.options.setProperty("gui.formatPwords.getText", "" + this.formatPwords.getText());
/*  639 */     this.options.setProperty("gui.formatPwords.isEnabled", "" + this.formatPwords.isEnabled());
/*  640 */     this.options.setProperty("gui.formatting_Help_pane.isEnabled", "" + this.formatting_Help_pane.isEnabled());
/*  641 */     this.options.setProperty("gui.formatting_Help_scroll.isEnabled", "" + this.formatting_Help_scroll.isEnabled());
/*      */     
/*  643 */     if (!this.gen.running)
/*      */     {
/*      */       try
/*      */       {
/*  647 */         String RetTxt = this.editorArea.getText().toString();
/*      */         
/*      */ 
/*  650 */         JFileChooser chooser = new JFileChooser();
/*  651 */         chooser.setMultiSelectionEnabled(false);
/*      */         
/*  653 */         if (this.saveToFile != null) {
/*  654 */           chooser.setSelectedFile(this.saveToFile);
/*      */         }
/*      */         
/*      */ 
/*  658 */         chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
/*      */           public boolean accept(File f) {
/*  660 */             return f.getName().endsWith(".cfg");
/*      */           }
/*      */           
/*      */           public String getDescription() {
/*  664 */             return "Configuration Files";
/*      */           }
/*  666 */         });
/*  667 */         chooser.setAcceptAllFileFilterUsed(false);
/*  668 */         int returnVal = chooser.showSaveDialog(this);
/*      */         
/*      */ 
/*  671 */         if (returnVal == 0) {
/*  672 */           this.saveToFile = chooser.getSelectedFile();
/*      */           
/*  674 */           if (!this.saveToFile.getCanonicalPath().endsWith(".cfg")) {
/*  675 */             this.saveToFile = new File(this.saveToFile.getAbsolutePath() + ".cfg");
/*      */           }
/*      */           
/*  678 */           if (!this.saveToFile.exists()) {
/*  679 */             this.saveToFile.createNewFile();
/*      */           }
/*  681 */           java.io.OutputStream outStream = new java.io.FileOutputStream(this.saveToFile.getAbsoluteFile());
/*  682 */           this.options.storeToXML(outStream, "Java Password Generator Options");
/*      */         }
/*      */       }
/*      */       catch (java.io.IOException e) {
/*  686 */         System.out.println(e);
/*      */       } catch (NullPointerException n) {
/*  688 */         System.out.println(n);
/*      */       } catch (Exception l) {
/*  690 */         System.out.println(l);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void about_ButtonMouseClicked(MouseEvent evt)
/*      */   {
/*  700 */     javax.swing.JOptionPane.showMessageDialog(this, "Java Password Generator\nGUI Version " + guiVersion + "\nGenerator Version " + Generator.genVersion + "\n\nThis software is licenced under the GNU GPL.", "About Java Password Generator", 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void allowDuplicatesMouseClicked(MouseEvent evt)
/*      */   {
/*  708 */     this.gen.allowDuplicatePasswords = this.allowDuplicates.isSelected();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void licence_ButtonMouseClicked(MouseEvent evt)
/*      */   {
/*  716 */     if (!this.licence_Button.isSelected())
/*      */     {
/*  718 */       this.editorArea.setText(this.previousText);
/*  719 */       this.previousText = "";
/*      */     }
/*      */     else
/*      */     {
/*      */       try {
/*  724 */         jPasswordGenerator.licence theLicence = new jPasswordGenerator.licence("gpl", 3.0D);
/*      */         
/*  726 */         this.previousText = this.editorArea.getText();
/*  727 */         String licenceFileName = "licence.txt";
/*      */         try {
/*  729 */           theLicence.loadLicenceFromWeb();
/*      */         } catch (Exception ex1) {
/*  731 */           echo("Couldn't load the licence from the web at the following URL:");
/*  732 */           echo(theLicence.getWebURL());
/*      */           try {
/*  734 */             theLicence.loadLicenceFromFile(licenceFileName);
/*      */           }
/*      */           catch (Exception ex) {
/*  737 */             echo("Couldn't find the licence file " + licenceFileName + " -- getting it from the web.");
/*      */             
/*  739 */             String newText = "We were unable to load the licence from both the web and\n";
/*  740 */             newText = newText + "from the local file named '" + licenceFileName + "'.  However,\n";
/*  741 */             newText = newText + "this software is licenced under the GNU GPL Version 3.\n";
/*  742 */             newText = newText + "Details can be found at gnu.org.";
/*  743 */             theLicence.setLicenceText(newText);
/*      */           }
/*      */         }
/*  746 */         this.editorArea.setText(theLicence.getLicenceText());
/*      */       } catch (Exception ex) {
/*  748 */         java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void save_ButtonMouseClicked(MouseEvent evt)
/*      */   {
/*  758 */     if (!this.gen.running)
/*      */     {
/*      */       try
/*      */       {
/*  762 */         String RetTxt = this.editorArea.getText().toString();
/*      */         
/*      */ 
/*  765 */         JFileChooser chooser = new JFileChooser();
/*  766 */         chooser.setMultiSelectionEnabled(false);
/*  767 */         if (this.saveToFile != null) {
/*  768 */           chooser.setSelectedFile(this.saveToFile);
/*      */         }
/*      */         
/*      */ 
/*  772 */         int returnVal = chooser.showSaveDialog(this);
/*      */         
/*      */ 
/*  775 */         if (returnVal == 0) {
/*  776 */           this.saveToFile = chooser.getSelectedFile();
/*  777 */           if (!this.saveToFile.exists()) {
/*  778 */             this.saveToFile.createNewFile();
/*      */           }
/*  780 */           java.io.FileWriter outFile = new java.io.FileWriter(this.saveToFile.getAbsoluteFile());
/*  781 */           outFile.write(RetTxt.replace("\n", this.gen.systemLineSeparator));
/*  782 */           this.isSaved = true;
/*  783 */           outFile.close();
/*      */         }
/*      */       } catch (java.io.IOException e) {
/*  786 */         System.out.println(e);
/*      */       } catch (NullPointerException n) {
/*  788 */         System.out.println(n);
/*      */       } catch (Exception l) {
/*  790 */         System.out.println(l);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void close_ButtonMouseClicked(MouseEvent evt)
/*      */   {
/*  800 */     if ((this.gen.running) || (this.close_Button.getText().compareToIgnoreCase("Cancel") == 0)) {
/*  801 */       this.gen.stopGenerating();
/*      */     } else {
/*  803 */       System.exit(0);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  810 */   public void close() { dispose(); }
/*      */   
/*      */   public JTextField minWordLen_box;
/*      */   public JTextField numberPwords;
/*      */   public JLabel numberPwords_Label;
/*      */   public JPanel optionsPanel;
/*      */   public JScrollPane optionsScrollPane;
/*      */   
/*      */   private void generate_ButtonMouseClicked(MouseEvent evt) {
/*  819 */     if (this.licence_Button.isSelected()) {
/*  820 */       this.licence_Button.setSelected(false);
/*  821 */       licence_ButtonMouseClicked(evt);
/*      */     }
/*  823 */     if (this.generate_Button.getText().equalsIgnoreCase("Generate")) {
/*  824 */       generatePwords();
/*  825 */     } else if (this.generate_Button.getText().equalsIgnoreCase("Stop")) {
/*  826 */       this.gen.stopGenerating();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void generatePwords()
/*      */   {
/*  834 */     String format = this.formatPwords.getText();
/*  835 */     long nTimesInt = Long.parseLong(this.numberPwords.getText());
/*  836 */     int nCharsInt = Integer.parseInt(this.lengthPwords.getText());
/*  837 */     String explChars = this.explicitChars.getText();
/*  838 */     String incChars = this.includeChars.getText();
/*  839 */     String excChars = this.excludeChars.getText();
/*  840 */     boolean useWords = this.useWords_checkbox.isSelected();
/*  841 */     int maxWordLen = Integer.parseInt(this.maxWordLen_box.getText());
/*  842 */     int minWordLen = Integer.parseInt(this.minWordLen_box.getText());
/*      */     
/*  844 */     this.gen.allowDuplicatePasswords = this.allowDuplicates.isSelected();
/*  845 */     if (this.charSet.getSelectedItem().toString().compareToIgnoreCase("User Defined - Formatted") == 0) {
/*  846 */       echo("Format generating passwords.");
/*  847 */       this.gen.setOptions(nTimesInt, 0, format, explChars, incChars, excChars, useWords, minWordLen, maxWordLen);
/*  848 */       this.gen.setFormatted(true);
/*      */     } else {
/*  850 */       echo("Randomly generating passwords.");
/*  851 */       this.gen.setOptions(nTimesInt, nCharsInt, "", explChars, incChars, excChars, useWords, minWordLen, maxWordLen);
/*  852 */       this.gen.setFormatted(false);
/*      */     }
/*  854 */     this.threadExecutor.execute(this.gen);
/*      */   }
/*      */   
/*      */   public JProgressBar progressBar;
/*      */   public JPanel pwordOptionsPanel;
/*      */   public JButton saveSettingsButton;
/*      */   public JButton save_Button;
/*      */   public javax.swing.JSplitPane splitPane;
/*      */   public JCheckBox useWords_checkbox;
/*  863 */   private void updateStatus() { if (this.gen.running) {
/*  864 */       this.progressBar.setMinimum(0);
/*  865 */       this.progressBar.setMaximum(100);
/*  866 */       this.progressBar.setValue(this.gen.statusInt);
/*  867 */       this.progressBar.setString(this.gen.statusStr);
/*  868 */       this.generate_Button.setText("Stop");
/*  869 */       if (this.gen.statusInt >= 0) {
/*      */         try {
/*  871 */           String lastGenPword = this.gen.getLastGeneratedPassword();
/*  872 */           String[] pwords = this.editorArea.getText().split("\n");
/*  873 */           if (pwords[(pwords.length - 1)].compareTo(lastGenPword) != 0) {
/*  874 */             this.editorArea.append(this.gen.getLastGeneratedPassword() + "\n");
/*      */           }
/*      */         } catch (Exception ex) {
/*  877 */           ex.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void updateStatusComplete()
/*      */   {
/*  888 */     this.progressBar.setMinimum(0);
/*  889 */     this.progressBar.setMaximum(100);
/*  890 */     this.progressBar.setValue(0);
/*  891 */     this.progressBar.setString(this.gen.statusStr);
/*  892 */     this.generate_Button.setEnabled(true);
/*  893 */     this.generate_Button.setText("Generate");
/*  894 */     this.editorArea.setText(this.gen.getGeneratedPasswords());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void charSetItemStateChanged(java.awt.event.ItemEvent evt)
/*      */   {
/*  902 */     String newCharSet = this.charSet.getSelectedItem().toString();
/*  903 */     if (newCharSet.compareToIgnoreCase("User Defined - Formatted") != 0) {
/*  904 */       unsetUserDefinedFormattedOptions();
/*      */     }
/*      */     
/*  907 */     if (newCharSet.compareToIgnoreCase("Alpha-Numeric Only") == 0) {
/*  908 */       echo("Is Alpha-Numeric Only");
/*  909 */       this.explicitChars.setText(Generator.alphaCharSet.toLowerCase() + Generator.alphaCharSet.toUpperCase() + Generator.numerCharSet);
/*  910 */       setExplicitCharsEnabled(false);
/*  911 */     } else if (newCharSet.compareToIgnoreCase("Alpha-Numeric Easy to Read") == 0) {
/*  912 */       echo("Is Alpha-Numeric Easy to Read");
/*  913 */       this.explicitChars.setText(Generator.easyReadAlphaCharSet.toLowerCase() + Generator.easyReadAlphaCharSet.toUpperCase() + Generator.easyReadNumerCharSet);
/*  914 */       setExplicitCharsEnabled(false);
/*  915 */     } else if (newCharSet.compareToIgnoreCase("Alpha-Numeric and Symbols") == 0) {
/*  916 */       echo("Is Alpha-Numeric and Symbols");
/*  917 */       this.explicitChars.setText(Generator.alphaCharSet.toLowerCase() + Generator.alphaCharSet.toUpperCase() + Generator.numerCharSet + Generator.symblCharSet);
/*  918 */       setExplicitCharsEnabled(false);
/*  919 */     } else if (newCharSet.compareToIgnoreCase("Symbols Only") == 0) {
/*  920 */       echo("Is Symbols Only");
/*  921 */       this.explicitChars.setText(Generator.symblCharSet);
/*  922 */       setExplicitCharsEnabled(false);
/*  923 */     } else if (newCharSet.compareToIgnoreCase("User Defined - All Random") == 0) {
/*  924 */       echo("Is User Defined - All Random");
/*  925 */       this.explicitChars.setText("");
                 setUserDefinedFormattedOptions();
/*  926 */       setExplicitCharsEnabled(true);
/*  927 */     } else if (newCharSet.compareToIgnoreCase("User Defined - Formatted") == 0) {
/*  928 */       echo("Is User Defined - Formatted");
/*  929 */       setExplicitCharsEnabled(true);
/*  930 */       setUserDefinedFormattedOptions();
/*      */     }
/*      */   }
/*      */   
/*      */   private void jButton1ActionPerformed(ActionEvent evt)
/*      */   {
/*  936 */     this.editorArea.setText("");
/*      */   }
/*      */   
/*      */ 
/*      */   private void useWords_checkboxActionPerformed(ActionEvent evt) {}
/*      */   
/*      */ 
/*      */   private void formatPwordsActionPerformed(ActionEvent evt)
/*      */   {
/*  945 */     String val = this.formatPwords.getText();
/*  946 */     if (val.contains("^")) {
/*  947 */       this.useWords_checkbox.setSelected(true);
/*      */     } else {
/*  949 */       this.useWords_checkbox.setSelected(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void setExplicitCharsEnabled(boolean newVal)
/*      */   {
/*  957 */     this.explicitChars_Scroll.setEnabled(newVal);
/*  958 */     this.explicitChars.setEnabled(newVal);
/*      */   }
/*      */   
/*      */ 
/*      */   private void setUserDefinedFormattedOptions()
/*      */   {
/*  964 */     this.explicitChars_Scroll.setBorder(BorderFactory.createTitledBorder(null, "Alpha Characters (@)", 0, 0, new Font("Dialog", 1, 12)));
/*  965 */     this.explicitChars.setText(Generator.alphaCharSet.toLowerCase() + Generator.alphaCharSet.toUpperCase());
/*  966 */     this.includeChars_Scroll.setBorder(BorderFactory.createTitledBorder(null, "Numeric Characters (#)", 0, 0, new Font("Dialog", 1, 12)));
/*  967 */     this.includeChars.setText(Generator.numerCharSet);
/*  968 */     this.excludeChars_Scroll.setBorder(BorderFactory.createTitledBorder(null, "Symbol Characters (%)", 0, 0, new Font("Dialog", 1, 12)));
/*  969 */     this.excludeChars.setText(Generator.symblCharSet);
/*  970 */     this.formattingPanel.setEnabled(true);
/*  971 */     this.formatPwords.setEnabled(true);
/*  972 */     this.formatting_Help_scroll.setEnabled(true);
/*  973 */     this.formatting_Help_pane.setEnabled(true);
/*  974 */     //this.lengthPwords.setEnabled(false);
/*  975 */     this.lengthPwords_Label.setEnabled(false);
/*      */   }
/*      */   
/*      */ 
/*      */   private void unsetUserDefinedFormattedOptions()
/*      */   {
/*  981 */     this.explicitChars_Scroll.setBorder(BorderFactory.createTitledBorder(null, "Explicit Characters", 0, 0, new Font("Dialog", 1, 12)));
/*  982 */     this.includeChars_Scroll.setBorder(BorderFactory.createTitledBorder(null, "Include Characters", 0, 0, new Font("Dialog", 1, 12)));
/*  983 */     this.excludeChars_Scroll.setBorder(BorderFactory.createTitledBorder(null, "Exclude Characters", 0, 0, new Font("Dialog", 1, 12)));
/*  984 */     this.formatPwords.setEnabled(false);
/*  985 */     this.formattingPanel.setEnabled(false);
/*  986 */     this.formatting_Help_scroll.setEnabled(false);
/*  987 */     this.formatting_Help_pane.setEnabled(false);
/*  988 */     this.lengthPwords.setEnabled(true);
/*  989 */     this.lengthPwords_Label.setEnabled(true);
/*      */   }
/*      */   
/*      */ 
/*      */   private void validateNumberPwords()
/*      */   {
/*  995 */     this.numberPwords.setText(integersOnly(this.numberPwords.getText()));
/*      */   }
/*      */   
/*      */   private String integersOnly(String x) {
/*  999 */     String outStr = "";
/* 1000 */     for (int i = 0; i < x.length(); i++) {
/* 1001 */       if (Generator.numerCharSet.contains("" + x.charAt(i))) {
/* 1002 */         echo(Generator.numerCharSet + " contains " + x.charAt(i));
/* 1003 */         outStr = outStr + x.charAt(i);
/*      */       }
/*      */     }
/* 1006 */     return outStr;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setGenerator(Generator newGen)
/*      */   {
/* 1013 */     this.gen = newGen;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void echo(Object x)
/*      */   {
/* 1021 */     System.out.println(x.toString());
/*      */   }
/*      */ }


/* Location:              C:\Users\Mathew\Desktop\jPasswordGenerator.jar!\jPasswordGenerator\gui\MainFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */