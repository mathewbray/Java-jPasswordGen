/*     */ package jPasswordGenerator;
/*     */ 
/*     */ import jPasswordGenerator.gui.MainFrame;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Properties;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Main
/*     */ {
/*     */   public MainFrame mainFrame;
/*     */   public Generator gen;
/*     */   public boolean guiMode;
/*     */   private String nCharsStr;
/*     */   private String nTimesStr;
/*     */   private String excCharsS;
/*     */   private String incCharsS;
/*     */   private String useCharsS;
/*     */   private String useChars;
/*     */   private int excCharsQ;
/*     */   private int useCharsQ;
/*     */   private int nCharsInt;
/*     */   private int nTimesInt;
/*     */   private Object selectedValue;
/*  45 */   private String defChars = " ";
/*  46 */   private String generatedPwords = "";
/*  47 */   public String alphaChars = "abcdefghijklmnopqrstuvwxyz";
/*  48 */   public String numerChars = "0123456789";
/*  49 */   public String easyReadAlphaChars = "abcdefghjkmnpqrstuvwxyz";
/*  50 */   public String easyReadNumerChars = "23456789";
/*  51 */   public String symblChars = "!@#$%^&*()_-+={}[]|\\;:'\"<>.?/~`";
/*     */   private String outFileStr;
/*  53 */   private String errText = "";
/*     */   private File outFile;
/*     */   private File propFile;
/*  56 */   private Properties options = new Properties();
/*  57 */   private boolean forceOutFile = false;
/*  58 */   public double requiredJavaVersion = 1.6D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Main(String[] args)
/*     */   {
/*  70 */     if (Double.parseDouble(System.getProperty("java.specification.version")) < this.requiredJavaVersion) {
/*  71 */       echo("ERROR: Cannot run because the JVM is not version " + this.requiredJavaVersion + " or higher.\n" + "  FIX: Update your java installation.");
/*     */       
/*  73 */       if (args[0].compareToIgnoreCase("--gui") == 0) {
/*  74 */         JOptionPane.showMessageDialog(null, "Cannot run because your JRE does not meet the requirements.\nPlease install a JRE of at least version " + this.requiredJavaVersion + ".", "ERROR", 0);
/*     */       }
/*  76 */       System.exit(1);
/*     */     } else {
/*  78 */       echo("JRE version is OK.  Continuing happily.");
/*     */     }
/*     */     
/*  81 */     this.gen = new Generator()
/*     */     {
/*     */       public void onProgressUpdate() {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       public void onComplete() {}
/*  92 */     };
/*  93 */     clearVals();
/*  94 */     this.guiMode = true;
/*  95 */     if (args.length > 0) {
/*  96 */       for (int i = 0; i < args.length; i++) {
/*  97 */         if (args[i].compareToIgnoreCase("--gui") == 0) {
/*  98 */           this.guiMode = true;
/*  99 */           break;
/*     */         }
/* 101 */         if (args[i].compareToIgnoreCase("--nogui") == 0) {
/* 102 */           this.guiMode = false;
/*     */         }
/*     */       }
/*     */     }
/* 106 */     if (this.guiMode) {
/* 107 */       for (int i = 0; i < args.length; i++) {
/* 108 */         if (args[i].compareToIgnoreCase("--cfgFile") == 0) {
/* 109 */           this.propFile = new File(args[(i++)]);
/*     */         }
/*     */       }
/* 112 */       initMainGUI();
/*     */     } else {
/* 114 */       initCommandLineInterface(args);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean hasReqArgs()
/*     */   {
/* 126 */     boolean retBool = true;
/* 127 */     if (this.useChars.isEmpty()) {
/* 128 */       retBool = false;
/* 129 */       this.errText += "ERROR:  YOU MUST ENTER WHAT CHARACTERS YOU WISH TO USE,\n        OR ELSE, DEFINE A CHARSET TO USE.\n";
/*     */     }
/* 131 */     if ((this.nCharsStr.isEmpty()) || (this.nTimesStr.isEmpty())) {
/* 132 */       retBool = false;
/* 133 */       this.errText += "ERROR:  YOU MUST ENTER BOTH THE LENGTH OF EACH PASSWORD AND\n        THE NUMBER OF PASSWORDS YOU WISH TO GENERATE.\n";
/*     */     }
/* 135 */     if (this.propFile != null) {
/* 136 */       if (this.propFile.exists()) {
/* 137 */         retBool = true;
/* 138 */         this.errText = "";
/*     */         try {
/* 140 */           InputStream inStream = new FileInputStream(this.propFile);
/* 141 */           this.options.loadFromXML(inStream);
/* 142 */           this.gen.readNewProperties(this.options);
/*     */         } catch (Exception e) {
/* 144 */           this.errText += e.getMessage();
/* 145 */           retBool = false;
/*     */         }
/*     */       } else {
/* 148 */         this.errText = ("The configuration file \"" + this.propFile.getAbsolutePath() + "\"\ndoes not exist.");
/*     */       }
/*     */     }
/* 151 */     return retBool;
/*     */   }
/*     */   
/*     */ 
/*     */   private void clearVals()
/*     */   {
/* 157 */     this.nCharsStr = "";
/* 158 */     this.nTimesStr = "";
/* 159 */     this.excCharsS = "";
/* 160 */     this.incCharsS = "";
/* 161 */     this.useCharsS = "";
/* 162 */     this.useChars = "";
/* 163 */     this.excCharsQ = 0;
/* 164 */     this.useCharsQ = 0;
/* 165 */     this.nCharsInt = 8;
/* 166 */     this.nTimesInt = 10;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initMainGUI()
/*     */   {
/* 174 */     if (this.propFile != null) {
/* 175 */       this.mainFrame = new MainFrame(this.options);
/*     */     } else {
/* 177 */       this.mainFrame = new MainFrame();
/*     */     }
/* 179 */     this.mainFrame.setVisible(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 188 */     new Main(args);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int parseArgs(String[] args)
/*     */   {
/* 200 */     int retInt = 0;
/* 201 */     echo("There were " + args.length + " arguments supplied.");
/* 202 */     this.guiMode = true;
/* 203 */     for (int i = 1; i < args.length; i++) {
/* 204 */       if ((args[i].compareToIgnoreCase("--chars") == 0) && (args.length > i + 1)) {
/* 205 */         i++;
/* 206 */         this.useChars = args[i];
/* 207 */         echo("Chars: " + this.useChars);
/*     */       }
/* 209 */       else if ((args[i].compareToIgnoreCase("--len") == 0) && (args.length > i + 1)) {
/* 210 */         i++;
/* 211 */         this.nCharsStr = args[i];
/* 212 */         this.nCharsInt = Integer.parseInt(this.nCharsStr);
/* 213 */         echo("Length: " + this.nCharsStr);
/* 214 */       } else if ((args[i].compareToIgnoreCase("--num") == 0) && (args.length > i + 1)) {
/* 215 */         i++;
/* 216 */         this.nTimesStr = args[i];
/* 217 */         this.nTimesInt = Integer.parseInt(this.nTimesStr);
/* 218 */         echo("Number: " + this.nTimesStr);
/* 219 */       } else if ((args[i].compareToIgnoreCase("--charSet") == 0) && (args.length > i + 1)) {
/* 220 */         i++;
/* 221 */         if (args[i].compareToIgnoreCase("1") == 0) {
/* 222 */           this.defChars = (this.alphaChars.toLowerCase() + this.alphaChars.toUpperCase() + this.numerChars);
/*     */         }
/*     */         
/*     */ 
/* 226 */         if (args[i].compareToIgnoreCase("2") == 0) {
/* 227 */           this.defChars = (this.easyReadAlphaChars.toLowerCase() + this.easyReadAlphaChars.toUpperCase() + this.easyReadNumerChars);
/*     */         }
/*     */         
/*     */ 
/* 231 */         if (args[i].compareToIgnoreCase("3") == 0) {
/* 232 */           this.defChars = (this.alphaChars.toLowerCase() + this.alphaChars.toUpperCase() + this.numerChars + this.symblChars);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 238 */         if (args[i].compareToIgnoreCase("4") == 0) {
/* 239 */           this.defChars = this.symblChars;
/*     */         }
/* 241 */         this.useChars += this.defChars;
/* 242 */         echo("Pre-Defined Chars: " + this.defChars);
/* 243 */       } else if ((args[i].compareToIgnoreCase("--charExc") == 0) && (args.length > i + 1)) {
/* 244 */         i++;
/* 245 */         this.excCharsS = args[i];
/* 246 */         echo("Excluded Chars: " + this.excCharsS);
/* 247 */       } else if ((args[i].compareToIgnoreCase("--charInc") == 0) && (args.length > i + 1)) {
/* 248 */         i++;
/* 249 */         this.incCharsS = args[i];
/* 250 */         this.useChars += this.incCharsS;
/* 251 */         echo("Included Chars: " + this.incCharsS);
/* 252 */       } else if ((args[i].compareToIgnoreCase("--file") == 0) && (args.length > i + 1)) {
/* 253 */         i++;
/* 254 */         this.outFileStr = args[i];
/* 255 */         echo("Output to File: " + this.outFileStr);
/* 256 */       } else if ((args[i].compareToIgnoreCase("--cfgFile") == 0) && (args.length > i + 1)) {
/* 257 */         this.propFile = new File(args[(i + 1)]);
/* 258 */         i++;
/* 259 */       } else if (args[i].compareToIgnoreCase("--gui") == 0) {
/* 260 */         echo("Scratch all other args -- booting to gui mode.");
/* 261 */         this.guiMode = true;
/* 262 */       } else if (args[i].compareToIgnoreCase("--nogui") == 0) {
/* 263 */         echo("Using non-GUI mode.");
/* 264 */         this.guiMode = false;
/* 265 */         retInt = 0;
/* 266 */       } else if (args[i].compareToIgnoreCase("--force") == 0) {
/* 267 */         this.forceOutFile = true;
/* 268 */       } else if (args[i].compareToIgnoreCase("--help") == 0) {
/* 269 */         this.guiMode = false;
/* 270 */         retInt = -1;
/*     */       } else {
/* 272 */         echo("\"" + args[i] + "\" is not a valid argument.");
/*     */       }
/*     */     }
/*     */     
/* 276 */     if (this.guiMode) {
/* 277 */       retInt = 0;
/*     */     } else {
/* 279 */       retInt = 1;
/*     */     }
/*     */     
/* 282 */     return retInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void printHelp()
/*     */   {
/* 289 */     echo("Welcome to the java Password Generator.  Below are the options you can use.");
/* 290 */     echo("X    --gui        Uses the graphical mode.  Uses this mode by default.");
/* 291 */     echo("                 MUST BE FIRST ARGUMENT.  ALL OTHER ARGS IGNORED IF SET.");
/* 292 */     echo("X    --nogui      Uses the text mode.");
/* 293 */     echo("                 MUST BE FIRST ARGUMENT.");
/* 294 */     echo("X    --chars      Tells the generator what characters to use.");
/* 295 */     echo("                 TEXT MODE ONLY.");
/* 296 */     echo("X    --num        Tells the generator how many passwords to make.");
/* 297 */     echo("                 TEXT MODE ONLY.");
/* 298 */     echo("X    --len        Tells the generator how long to make each password.");
/* 299 */     echo("                 TEXT MODE ONLY.");
/* 300 */     echo("X    --charSet    Tells the generator what set of pre-defined characters to use");
/* 301 */     echo("                 based on the following values.  Added to values of '--chars'");
/* 302 */     echo("                 and '--charInc'.");
/* 303 */     echo("                 TEXT MODE ONLY.");
/* 304 */     echo("                    1 -> Alpha-Numeric Only");
/* 305 */     echo("                    2 -> Alpha-Numeric Easy-To-Read");
/* 306 */     echo("                    3 -> Alpha-Numeric and Symbols");
/* 307 */     echo("                    4 -> Symbols Only");
/* 308 */     echo("X    --excChars   Tells the generator what characters not to use.");
/* 309 */     echo("                 TEXT MODE ONLY.");
/* 310 */     echo("X    --incChars   Tells the generator what characters to include, if using '--charSet'.");
/* 311 */     echo("                 TEXT MODE ONLY.");
/* 312 */     echo("X    --file       Tells the generator where to put the generated passwords.");
/* 313 */     echo("                 TEXT MODE ONLY.  NOT IMPLEMENTED YET.");
/* 314 */     echo("X    --force      Forces the output of the passwords to a file, whether that file");
/* 315 */     echo("                 exists or not.");
/* 316 */     echo("X    --cfgFile    Reads generator options from the specified file.  All other");
/* 317 */     echo("                 specified options take precedence over the config file.");
/* 318 */     echo("                 TEXT MODE ONLY.");
/* 319 */     echo("X    --formatted  Tells the generator that you want to make the passwords according");
/* 320 */     echo("                 to the following argument's value. ex: --formatted ###-###-####");
/* 321 */     echo("                 will produce a set of phone numbers.");
/* 322 */     echo("X    --alphaChars Tells the generator what characters to use to replace '@' characters.");
/* 323 */     echo("X    --symblChars Tells the generator what characters to use to replace '%' characters.");
/* 324 */     echo("X    --numerChars Tells the generator what characters to use to replace '#' characters.");
/* 325 */     echo("    --wizard     In TEXT MODE, use a wizard where the generator options are");
/* 326 */     echo("                 set by you dynamically. NOT IMPLEMENTED YET.");
/* 327 */     echo("X    --help       Displays this help message.");
/*     */   }
/*     */   
/*     */   private void initCommandLineInterface(String[] args)
/*     */   {
/* 332 */     echo("Entering command line interface.");
/* 333 */     for (int i = 0; i < args.length; i++) {
/* 334 */       if (args[i].compareToIgnoreCase("--chars") == 0) {
/* 335 */         this.gen.incChars += args[(++i)];
/* 336 */       } else if (args[i].compareToIgnoreCase("--charSet") == 0) {
/* 337 */         String charSetType = args[(++i)];
/* 338 */         if (charSetType.compareToIgnoreCase("1") == 0) {
/* 339 */           Generator tmp98_95 = this.gen;tmp98_95.incChars = (tmp98_95.incChars + Generator.alphaCharSet.toLowerCase() + Generator.numerCharSet);
/* 340 */           this.gen.incChars += Generator.alphaCharSet.toUpperCase();
/* 341 */         } else if (charSetType.compareToIgnoreCase("2") == 0) {
/* 342 */           Generator tmp197_194 = this.gen;tmp197_194.incChars = (tmp197_194.incChars + Generator.easyReadAlphaCharSet.toLowerCase() + Generator.easyReadNumerCharSet);
/* 343 */           this.gen.incChars += Generator.easyReadAlphaCharSet.toUpperCase();
/* 344 */         } else if (charSetType.compareToIgnoreCase("3") == 0) {
/* 345 */           Generator tmp296_293 = this.gen;tmp296_293.incChars = (tmp296_293.incChars + Generator.alphaCharSet.toLowerCase() + Generator.numerCharSet + Generator.symblCharSet);
/* 346 */           this.gen.incChars += Generator.alphaCharSet.toUpperCase();
/* 347 */         } else if (charSetType.compareToIgnoreCase("4") == 0) {
/* 348 */           this.gen.incChars += Generator.symblCharSet;
/*     */         }
/* 350 */       } else if (args[i].compareToIgnoreCase("--excChars") == 0) {
/* 351 */         this.gen.excChars += args[(++i)];
/* 352 */       } else if (args[i].compareToIgnoreCase("--incChars") == 0) {
/* 353 */         this.gen.incChars += args[(++i)];
/* 354 */       } else if (args[i].compareToIgnoreCase("--num") == 0) {
/* 355 */         this.gen.nTimesDbl = Double.parseDouble(args[(++i)]);
/* 356 */       } else if (args[i].compareToIgnoreCase("--len") == 0) {
/* 357 */         this.gen.nCharsDbl = Double.parseDouble(args[(++i)]);
/* 358 */       } else if (args[i].compareToIgnoreCase("--file") == 0) {
/* 359 */         this.outFileStr = args[(++i)];
/* 360 */       } else if (args[i].compareToIgnoreCase("--force") == 0) {
/* 361 */         this.forceOutFile = true;
/* 362 */       } else if (args[i].compareToIgnoreCase("--cfgFile") == 0) {
/* 363 */         this.propFile = new File(args[(++i)]);
/*     */         try {
/* 365 */           if ((this.forceOutFile) && 
/* 366 */             (!this.propFile.exists())) {
/* 367 */             this.propFile.createNewFile();
/*     */           }
/*     */           
/* 370 */           FileReader fReader = new FileReader(this.propFile);
/* 371 */           this.gen.genOptions.load(fReader);
/*     */         } catch (IOException ex) {
/* 373 */           echo("Could not read file.");
/*     */         }
/*     */       }
/* 376 */       else if (args[i].compareToIgnoreCase("--formatted") == 0) {
/* 377 */         this.gen.format = args[(++i)];
/* 378 */         this.gen.setFormatted(true);
/* 379 */       } else if (args[i].compareToIgnoreCase("--alphaChars") == 0) {
/* 380 */         this.gen.alphaChars = args[(++i)];
/* 381 */       } else if (args[i].compareToIgnoreCase("--numerChars") == 0) {
/* 382 */         this.gen.numerChars = args[(++i)];
/* 383 */       } else if (args[i].compareToIgnoreCase("--symblChars") == 0) {
/* 384 */         this.gen.symblChars = args[(++i)];
/* 385 */       } else if (args[i].compareToIgnoreCase("--help") == 0) {
/* 386 */         printHelp();
/* 387 */         break;
/*     */       }
/*     */     }
/* 390 */     this.gen.generatePasswords();
/* 391 */     echo("\n\nHere are your passwords:\n");
/* 392 */     echo(this.gen.getGeneratedPasswords());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void echo(Object x)
/*     */   {
/* 400 */     System.out.println(x.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\Mathew\Desktop\jPasswordGenerator.jar!\jPasswordGenerator\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */