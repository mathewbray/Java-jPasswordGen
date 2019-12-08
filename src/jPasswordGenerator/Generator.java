/*     */ package jPasswordGenerator;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Generator
/*     */   implements Runnable
/*     */ {
/*     */   protected double nTimesDbl;
/*     */   protected double nCharsDbl;
/*     */   protected int progress;
/*     */   protected String format;
/*     */   protected String alphaChars;
/*     */   protected String numerChars;
/*     */   protected String symblChars;
/*     */   protected String explChars;
/*     */   protected String incChars;
/*     */   protected String excChars;
/*     */   public String generatedPwords;
/*     */   public String[] passwords;
/*     */   protected boolean formatted;
/*     */   public boolean readable;
/*     */   public int readableMinSize;
/*     */   public int readableMaxSize;
/*     */   public BufferedReader dictionaryIO;
/*     */   public ArrayList wordList;
/*     */   public boolean running;
/*  40 */   public int statusInt = 0;
/*  41 */   public String statusStr = "";
/*  42 */   public boolean allowDuplicatePasswords = false;
/*  43 */   public String systemLineSeparator = System.getProperty("line.separator");
/*  44 */   public static String alphaCharSet = "abcdefghijklmnopqrstuvwxyz";
/*  45 */   public static String numerCharSet = "0123456789";
/*  46 */   public static String easyReadAlphaCharSet = "abcdefghjkmnpqrstuvwxyz";
/*  47 */   public static String easyReadNumerCharSet = "23456789";
/*  48 */   public static String symblCharSet = "!@#$%^&*()_-+={}[]|\\;:'\"<>.?/~`";
/*  49 */   public static String genVersion = "3.43.00";
/*  50 */   public Properties genOptions = new Properties();
/*     */   private long startTime;
/*     */   private long stopTime;
/*     */   private int pwordCount;
/*  54 */   private boolean dictionaryInitd = false;
/*     */   
/*     */   public Generator()
/*     */   {
/*  58 */     resetToDefaults();
/*     */   }
/*     */   
/*     */   public void resetToDefaults() {
/*  62 */     this.formatted = false;
/*  63 */     this.readable = false;
/*  64 */     this.readableMinSize = 0;
/*  65 */     this.readableMaxSize = 0;
/*  66 */     this.nTimesDbl = 10.0D;
/*  67 */     this.nCharsDbl = 8.0D;
/*  68 */     this.format = "";
/*  69 */     this.alphaChars = "";
/*  70 */     this.numerChars = "";
/*  71 */     this.symblChars = "";
/*  72 */     this.explChars = "";
/*  73 */     this.incChars = "";
/*  74 */     this.excChars = "";
/*  75 */     this.generatedPwords = "";
/*  76 */     this.progress = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Properties writeAllProperties()
/*     */   {
/*  84 */     this.genOptions.setProperty("gen.version", genVersion);
/*  85 */     this.genOptions.setProperty("gen.nTimesStr", "" + this.nTimesDbl);
/*  86 */     this.genOptions.setProperty("gen.nCharsStr", "" + this.nCharsDbl);
/*  87 */     this.genOptions.setProperty("gen.formatted", "" + this.formatted);
/*  88 */     this.genOptions.setProperty("gen.format", this.format);
/*  89 */     this.genOptions.setProperty("gen.alphaChars", this.alphaChars);
/*  90 */     this.genOptions.setProperty("gen.numerChars", this.numerChars);
/*  91 */     this.genOptions.setProperty("gen.symblChars", this.symblChars);
/*  92 */     this.genOptions.setProperty("gen.explChars", this.explChars);
/*  93 */     this.genOptions.setProperty("gen.incChars", this.incChars);
/*  94 */     this.genOptions.setProperty("gen.excChars", this.excChars);
/*  95 */     this.genOptions.setProperty("gen.generatedPwords", this.generatedPwords);
/*  96 */     this.genOptions.setProperty("gen.allowDuplicatePasswords", "" + this.allowDuplicatePasswords);
/*  97 */     this.genOptions.setProperty("gen.readable", "" + this.readable);
/*  98 */     this.genOptions.setProperty("gen.readableMaxSize", "" + this.readableMaxSize);
/*  99 */     this.genOptions.setProperty("gen.readableMinSize", "" + this.readableMinSize);
/* 100 */     return this.genOptions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readNewProperties(Properties newOpts)
/*     */   {
/* 107 */     this.nTimesDbl = Double.parseDouble(this.genOptions.getProperty("gen.nTimesStr", "" + this.nTimesDbl));
/* 108 */     this.nCharsDbl = Double.parseDouble(this.genOptions.getProperty("gen.nCharsStr", "" + this.nCharsDbl));
/* 109 */     this.formatted = Boolean.parseBoolean(this.genOptions.getProperty("gen.formatted", "" + this.formatted));
/* 110 */     this.format = this.genOptions.getProperty("gen.format", this.format);
/* 111 */     this.alphaChars = this.genOptions.getProperty("gen.alphaChars", this.alphaChars);
/* 112 */     this.numerChars = this.genOptions.getProperty("gen.numerChars", this.numerChars);
/* 113 */     this.symblChars = this.genOptions.getProperty("gen.symblChars", this.symblChars);
/* 114 */     this.explChars = this.genOptions.getProperty("gen.explChars", this.explChars);
/* 115 */     this.incChars = this.genOptions.getProperty("gen.incChars", this.incChars);
/* 116 */     this.excChars = this.genOptions.getProperty("gen.excChars", this.excChars);
/* 117 */     this.generatedPwords = this.genOptions.getProperty("gen.generatedPwords", this.generatedPwords);
/* 118 */     this.allowDuplicatePasswords = Boolean.parseBoolean(this.genOptions.getProperty("gen.allowDuplicatePasswords", "" + this.allowDuplicatePasswords));
/* 119 */     this.readable = Boolean.parseBoolean(this.genOptions.getProperty("gen.readable", "" + this.readable));
/* 120 */     this.readableMaxSize = Integer.parseInt(this.genOptions.getProperty("gen.readableMaxSize", "" + this.readableMaxSize));
/* 121 */     this.readableMinSize = Integer.parseInt(this.genOptions.getProperty("gen.readableMinSize", "" + this.readableMinSize));
/*     */   }
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
/*     */   public void setOptions(double L_nTimesInt, int L_nCharsInt, String L_format, String L_alphaChars, String L_numerChars, String L_symblChars)
/*     */   {
/* 135 */     this.nTimesDbl = L_nTimesInt;
/* 136 */     this.nCharsDbl = L_nCharsInt;
/* 137 */     this.format = L_format;
/* 138 */     this.alphaChars = L_alphaChars;
/* 139 */     this.numerChars = L_numerChars;
/* 140 */     this.symblChars = L_symblChars;
/* 141 */     this.explChars = L_alphaChars;
/* 142 */     this.incChars = L_numerChars;
/* 143 */     this.excChars = L_symblChars;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOptions(double L_nTimesInt, int L_nCharsInt, String L_format, String L_alphaChars, String L_numerChars, String L_symblChars, boolean pronouncable, int minWordLen, int maxWordLen)
/*     */   {
/* 156 */     setOptions(L_nTimesInt, L_nCharsInt, L_format, L_alphaChars, L_numerChars, L_symblChars);
/* 157 */     this.readable = pronouncable;
/* 158 */     this.readableMinSize = minWordLen;
/* 159 */     this.readableMaxSize = maxWordLen;
/*     */   }
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
/*     */   public String generatePasswords(int L_nTimesInt, String L_format, String L_alphaChars, String L_numerChars, String L_symblChars, boolean pronounceable, int minWordLen, int MaxWordLen)
/*     */   {
/* 174 */     setOptions(L_nTimesInt, 0, L_format, L_alphaChars, L_numerChars, L_symblChars, pronounceable, minWordLen, MaxWordLen);
/* 175 */     this.formatted = true;
/* 176 */     return generatePasswords();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generatePasswords(long L_nTimesInt, int L_nCharsInt, String L_explChars, String L_incChars, String L_excChars, boolean pronounceable, int minWordLen, int MaxWordLen)
/*     */   {
/* 189 */     setOptions(L_nTimesInt, L_nCharsInt, "", L_explChars, L_incChars, L_excChars, pronounceable, minWordLen, MaxWordLen);
/* 190 */     this.formatted = false;
/* 191 */     return generatePasswords();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generatePasswords()
/*     */   {
/* 199 */     this.running = true;
/* 200 */     this.statusInt = -1;
/* 201 */     Date now = new Date();
/* 202 */     Random randInt = new Random(9123847L + now.getTime());
/*     */     
/* 204 */     if ((this.readable) && (this.readableMaxSize > this.nCharsDbl)) {
/* 205 */       this.readableMaxSize = ((int)this.nCharsDbl);
/*     */     }
/* 207 */     if ((this.readable) && (this.readableMinSize < 0)) {
/* 208 */       this.readableMinSize = ((int)this.nCharsDbl / 2);
/* 209 */       if (this.readableMinSize > this.readableMaxSize) {
/* 210 */         this.readableMinSize = this.readableMaxSize;
/*     */       }
/*     */     }
/*     */     
/* 214 */     double totalPossiblePwords = 1.0D;
/* 215 */     String totalChars = this.alphaChars + this.numerChars + this.symblChars;
/*     */     
/* 217 */     if (this.formatted) {
/* 218 */       for (int j = 0; j < this.format.length(); j++) {
/* 219 */         if (this.format.charAt(j) == "\\".charAt(0)) {
/* 220 */           j++;
/* 221 */         } else if (this.format.charAt(j) == "^".charAt(0)) {
/* 222 */           this.readable = true;
/* 223 */         } else if (this.format.charAt(j) == "#".charAt(0)) {
/* 224 */           totalPossiblePwords *= this.numerChars.length();
/* 225 */         } else if (this.format.charAt(j) == "@".charAt(0)) {
/* 226 */           totalPossiblePwords *= this.alphaChars.length();
/* 227 */         } else if (this.format.charAt(j) == "%".charAt(0)) {
/* 228 */           totalPossiblePwords *= this.symblChars.length();
/* 229 */         } else if (this.format.charAt(j) == "*".charAt(0)) {
/* 230 */           totalPossiblePwords *= totalChars.length();
/*     */         }
/*     */       }
/*     */     } else {
/* 234 */       String preUseChars = this.explChars + this.incChars;
/* 235 */       String useChars = "";
/* 236 */       for (int i = 0; i < preUseChars.length(); i++) {
/* 237 */         if (!this.excChars.contains(preUseChars.charAt(i) + "")) {
/* 238 */           useChars = useChars + preUseChars.charAt(i);
/*     */         }
/*     */       }
/* 241 */       totalPossiblePwords = Math.pow(useChars.length(), this.nCharsDbl);
/*     */     }
/*     */     
/*     */ 
/* 245 */     if (this.readable) {
/*     */       try {
/* 247 */         loadDictionary();
/*     */       } catch (IOException ex) {
/* 249 */         ex.printStackTrace();
/*     */       }
/*     */     }
/* 252 */     if ((this.nTimesDbl >= totalPossiblePwords) && (!this.allowDuplicatePasswords))
/*     */     {
/* 254 */       setStatus("NOT POSSIBLE.  Allowing Duplicate Passwords.");
/* 255 */       onProgressUpdate();
/* 256 */       this.allowDuplicatePasswords = true;
/*     */     }
/*     */     
/* 259 */     this.generatedPwords = "";
/* 260 */     this.passwords = new String[(int)this.nTimesDbl];
/* 261 */     setStatus("Generating passwords...");
/* 262 */     this.pwordCount = 0;
/* 263 */     this.statusInt = 0;
/* 264 */     if (((this.formatted) || (this.nCharsDbl == 0.0D)) && (!this.format.isEmpty())) {
/* 265 */       this.nCharsDbl = this.format.length();
/*     */       
/* 267 */       this.statusInt = 0;
/*     */       
/*     */ 
/* 270 */       for (int i = 0; (i < this.nTimesDbl) && (this.running); i++) {
/* 271 */         int thisone = i + 1;
/* 272 */         this.statusInt = Math.round((float)Math.round(thisone / this.nTimesDbl * 100.0D));
/* 273 */         setStatus("Generating Passwords... (" + i + "/" + this.nTimesDbl + ")");
/* 274 */         onProgressUpdate();
/* 275 */         String thisPword = "";
/*     */         
/*     */         do
/*     */         {
/* 279 */           for (int j = 0; (j < this.format.length()) && (this.running); j++)
/*     */           {
/* 281 */             randInt.setSeed(Math.round(i * this.nCharsDbl * j + now.getTime() + randInt.nextInt()));
/* 282 */             if (this.format.charAt(j) == "\\".charAt(0)) {
/* 283 */               j++;
/* 284 */               thisPword = thisPword + this.format.charAt(j);
/* 285 */             } else if (this.format.charAt(j) == "^".charAt(0))
/*     */             {
/*     */               try {
/* 288 */                 thisPword = thisPword.toLowerCase();
/* 289 */                 int specialSize = this.readableMinSize + randInt.nextInt(this.readableMaxSize - this.readableMinSize);
/* 290 */                 String word = getWordOfLength(randInt.nextInt(this.wordList.size()), specialSize);
/*     */                 
/* 292 */                 String thisChar = "" + word.charAt(0);
/* 293 */                 thisPword = thisPword + thisChar.toUpperCase();
/* 294 */                 thisPword = thisPword + word.substring(1).toLowerCase();
/*     */               }
/*     */               catch (Exception e) {
/* 297 */                 e.printStackTrace();
/*     */               }
/*     */             }
/* 300 */             else if (this.format.charAt(j) == "#".charAt(0))
/*     */             {
/* 302 */               thisPword = thisPword + this.numerChars.charAt(randInt.nextInt(this.numerChars.length()));
/*     */             }
/* 304 */             else if (this.format.charAt(j) == "@".charAt(0)) {
/* 305 */               thisPword = thisPword + this.alphaChars.charAt(randInt.nextInt(this.alphaChars.length()));
/* 306 */             } else if (this.format.charAt(j) == "%".charAt(0)) {
/* 307 */               thisPword = thisPword + this.symblChars.charAt(randInt.nextInt(this.symblChars.length()));
/* 308 */             } else if (this.format.charAt(j) == "*".charAt(0)) {
/* 309 */               thisPword = thisPword + totalChars.charAt(randInt.nextInt(totalChars.length()));
/*     */             } else {
/* 311 */               thisPword = thisPword + this.format.charAt(j);
/*     */             }
/*     */           }
/* 314 */         } while ((this.generatedPwords.contains(thisPword + "\n")) && (!this.allowDuplicatePasswords));
/* 315 */         this.generatedPwords = (this.generatedPwords + thisPword + "\n");
/* 316 */         this.passwords[i] = thisPword;
/* 317 */         this.pwordCount += 1;
/* 318 */         onProgressUpdate();
/*     */       }
/*     */     }
/*     */     else {
/* 322 */       String preUseChars = this.explChars + this.incChars;
/* 323 */       String useChars = "";
/* 324 */       for (int i = 0; i < preUseChars.length(); i++) {
/* 325 */         if (!this.excChars.contains(preUseChars.charAt(i) + "")) {
/* 326 */           useChars = useChars + preUseChars.charAt(i);
/*     */         }
/*     */       }
/* 329 */       this.statusInt = 0;
/*     */       
/* 331 */       for (int i = 0; (i < this.nTimesDbl) && (this.running); i++)
/*     */       {
/* 333 */         int thisone = i + 1;
/* 334 */         this.statusInt = Math.round((float)Math.round(thisone / this.nTimesDbl * 100.0D));
/* 335 */         setStatus("Generating Passwords... (" + i + "/" + this.nTimesDbl + ")");
/* 336 */         onProgressUpdate();
/*     */         
/* 338 */         String thisPword = "";
/*     */         do {
/* 340 */           thisPword = "";
/* 341 */           int specialPoint = 0;
/* 342 */           int specialSize = 0;
/* 343 */           if (this.readable)
/*     */           {
/* 345 */             int lgMaxOffset = (int)this.nCharsDbl - this.readableMinSize;
/* 346 */             specialPoint = randInt.nextInt(lgMaxOffset);
/* 347 */             specialSize = this.readableMinSize;
/* 348 */             if (this.readableMinSize < this.readableMaxSize) {
/* 349 */               specialSize += randInt.nextInt(this.readableMaxSize - this.readableMinSize);
/*     */             }
/*     */           }
/* 352 */           for (int j = 0; (j < this.nCharsDbl) && (this.running); j++) {
/* 353 */             if ((this.readable) && (j == specialPoint)) {
/* 354 */               thisPword = thisPword.toLowerCase();
/* 355 */               String word = getWordOfLength(randInt.nextInt(this.wordList.size()), specialSize);
/*     */               
/* 357 */               String thisChar = "" + word.charAt(0);
/* 358 */               thisPword = thisPword + thisChar.toUpperCase();
/* 359 */               thisPword = thisPword + word.substring(1).toLowerCase();
/*     */               
/*     */ 
/*     */ 
/* 363 */               j = thisPword.length() - 1;
/*     */             }
/*     */             
/* 366 */             randInt.setSeed(Math.round(i * this.nCharsDbl * j + now.getTime() + randInt.nextInt()));
/* 367 */             thisPword = thisPword + useChars.charAt(randInt.nextInt(useChars.length()));
/*     */           }
/* 369 */         } while ((this.generatedPwords.contains(thisPword + "\n")) && (!this.allowDuplicatePasswords) && (this.running));
/*     */         
/* 371 */         this.generatedPwords = (this.generatedPwords + thisPword + "\n");
/*     */         
/* 373 */         this.pwordCount += 1;
/* 374 */         this.passwords[(this.pwordCount - 1)] = thisPword;
/* 375 */         onProgressUpdate();
/*     */       }
/*     */     }
/* 378 */     this.stopTime = new Date().getTime();
/*     */     
/* 380 */     String totalTimeStr = getGenTime();
/*     */     
/*     */ 
/* 383 */     if (!this.running) {
/* 384 */       setStatus("Password Generation Aborted!  Took " + totalTimeStr + ", but only generated " + this.pwordCount + " passwords.");
/*     */     } else {
/* 386 */       setStatus("Done Generating Passwords!  Took " + totalTimeStr + ".");
/*     */     }
/*     */     
/*     */ 
/* 390 */     this.running = false;
/* 391 */     onProgressUpdate();
/* 392 */     return this.generatedPwords;
/*     */   }
/*     */   
/*     */   public String getWordOfLength(int offset, int size) {
/* 396 */     String word = "";
/* 397 */     boolean found = false;
/* 398 */     int count = 0;
/*     */     do {
/* 400 */       word = (String)this.wordList.get(offset + count);
/* 401 */       if (word.length() == size) {
/* 402 */         found = true;
/*     */       } else {
/* 404 */         count++;
/* 405 */         if (offset + count >= this.wordList.size()) {
/* 406 */           count = offset - this.wordList.size();
/*     */         }
/*     */       }
/* 409 */     } while (!found);
/* 410 */     return word;
/*     */   }
/*     */   
/*     */   public void loadDictionary() throws IOException {
/* 414 */     if (!this.dictionaryInitd) {
/* 415 */       File thisDir = new File("");
/* 416 */       String filename = "dictionary.txt";
/* 417 */       thisDir = thisDir.getAbsoluteFile();
/* 418 */       if (!thisDir.isDirectory()) {
/* 419 */         thisDir = thisDir.getParentFile();
/*     */       }
/* 421 */       int parentsLeft = 5;
/*     */       File dictFile;
/*     */       do
/*     */       {
/* 425 */         dictFile = checkDirForFile(thisDir, filename);
/* 426 */         if (dictFile == null) {
/* 427 */           parentsLeft--;
/* 428 */           thisDir = thisDir.getParentFile();
/*     */         } else {
/* 430 */           parentsLeft = -1;
/*     */         }
/* 432 */       } while (parentsLeft > 0);
/* 433 */       if (dictFile != null)
/*     */       {
/* 435 */         this.dictionaryIO = new BufferedReader(new FileReader(dictFile.toURI().toURL().getPath()));
/* 436 */         setStatus("Initializing the Dictionary.");
/* 437 */         onProgressUpdate();
/* 438 */         String thisWord = "";
/* 439 */         this.wordList = new ArrayList();
/* 440 */         while ((thisWord = this.dictionaryIO.readLine()) != null) {
/* 441 */           if ((thisWord.length() <= this.readableMaxSize) && (thisWord.length() >= this.readableMinSize)) {
/* 442 */             this.wordList.add(thisWord);
/* 443 */             System.out.println("Dictionary added " + thisWord);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 448 */         this.dictionaryIO.close();
/*     */       }
/* 450 */       this.dictionaryInitd = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setStatus(String newStat)
/*     */   {
/* 456 */     this.statusStr = newStat;
/* 457 */     System.out.println(newStat);
/* 458 */     onProgressUpdate();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private File checkDirForFile(File dir, String filename)
/*     */   {
/* 466 */     File[] fileList = dir.listFiles();
/* 467 */     for (int i = 0; i < fileList.length; i++) {
/* 468 */       if (fileList[i].isDirectory()) {
/* 469 */         File retFile = null;
/* 470 */         retFile = checkDirForFile(fileList[i], filename);
/* 471 */         if (retFile != null) {
/* 472 */           return retFile;
/*     */         }
/*     */       }
/* 475 */       if (fileList[i].getName().compareToIgnoreCase(filename) == 0) {
/* 476 */         setStatus("Found requested file at " + fileList[i].getAbsolutePath() + ".");
/*     */         
/* 478 */         return fileList[i];
/*     */       }
/*     */     }
/*     */     
/* 482 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void stopGenerating()
/*     */   {
/* 490 */     this.running = false;
/* 491 */     onProgressUpdate();
/* 492 */     onComplete();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean setFormatted(boolean newVal)
/*     */   {
/* 500 */     boolean retVal = false;
/* 501 */     this.formatted = newVal;
/* 502 */     if (this.formatted == newVal) {
/* 503 */       retVal = true;
/*     */     } else {
/* 505 */       retVal = false;
/*     */     }
/* 507 */     return retVal;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getGeneratedPasswords()
/*     */   {
/* 515 */     return this.generatedPwords;
/*     */   }
/*     */   
/* 518 */   public String[] getGeneratedPasswordsArray() { return this.passwords; }
/*     */   
/*     */   public String getLastGeneratedPassword()
/*     */     throws Exception
/*     */   {
/* 523 */     return this.passwords[(this.pwordCount - 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/* 530 */     this.startTime = new Date().getTime();
/* 531 */     generatePasswords();
/* 532 */     onComplete();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getGenTime()
/*     */   {
/* 539 */     long totalMs = this.stopTime - this.startTime;
/* 540 */     String retStr = "";
/*     */     
/*     */ 
/* 543 */     int days = (int)(totalMs / 86400000L);
/* 544 */     long remainingMs = totalMs - days * 86400000;
/* 545 */     int hours = (int)(remainingMs / 3600000L);
/* 546 */     remainingMs -= hours * 3600000;
/* 547 */     int minutes = (int)(remainingMs / 60000L);
/* 548 */     remainingMs -= minutes * 60000;
/* 549 */     int seconds = (int)(remainingMs / 1000L);
/* 550 */     remainingMs -= seconds * 1000;
/* 551 */     if (days > 0) {
/* 552 */       retStr = retStr + days + " days, ";
/*     */     }
/* 554 */     if (hours > 0) {
/* 555 */       retStr = retStr + hours + " hours, ";
/*     */     }
/* 557 */     if (minutes > 0) {
/* 558 */       retStr = retStr + minutes + " minutes, ";
/*     */     }
/* 560 */     if (seconds > 0) {
/* 561 */       retStr = retStr + seconds + " seconds, ";
/*     */     }
/* 563 */     retStr = retStr + remainingMs + " milliseconds";
/*     */     
/* 565 */     return retStr;
/*     */   }
/*     */   
/*     */   public abstract void onProgressUpdate();
/*     */   
/*     */   public abstract void onComplete();
/*     */ }


/* Location:              C:\Users\Mathew\Desktop\jPasswordGenerator.jar!\jPasswordGenerator\Generator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */