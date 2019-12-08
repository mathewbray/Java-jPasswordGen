/*     */ package jPasswordGenerator;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ 
/*     */ public class licence
/*     */ {
/*     */   private URL licenceURL;
/*     */   private String stopPhrase;
/*     */   private String licenceText;
/*     */   private InputStream iStream;
/*     */   
/*     */   public licence(String LicenceName, double version) throws Exception
/*     */   {
/*  21 */     if (LicenceName.compareToIgnoreCase("GPL") == 0) {
/*  22 */       this.licenceURL = new URL("http://www.gnu.org/licences/" + LicenceName.toLowerCase() + "-" + version + ".txt");
/*  23 */       this.stopPhrase = "END OF TERMS AND CONDITIONS";
/*  24 */     } else if (LicenceName.compareToIgnoreCase("BSD") != 0) {}
/*     */   }
/*     */   
/*     */   public licence(String url, String haltPhrase) throws Exception
/*     */   {
/*  29 */     this.licenceURL = new URL(url);
/*  30 */     this.stopPhrase = haltPhrase;
/*     */   }
/*     */   
/*     */   public void setLicenceInfo(URL lURL, String haltPhrase) {
/*  34 */     this.licenceURL = lURL;
/*  35 */     this.stopPhrase = haltPhrase;
/*     */   }
/*     */   
/*     */   public void loadLicenceFromWeb() throws Exception {
/*  39 */     this.iStream = this.licenceURL.openStream();
/*  40 */     loadLicenceFromStream();
/*     */   }
/*     */   
/*     */   public void loadLicenceFromFile(String filename) throws Exception {
/*  44 */     File thisDir = new File("");
/*  45 */     thisDir = thisDir.getAbsoluteFile();
/*  46 */     if (!thisDir.isDirectory()) {
/*  47 */       thisDir = thisDir.getParentFile();
/*     */     }
/*  49 */     int parentsLeft = 5;
/*     */     File licenceFile;
/*     */     do
/*     */     {
/*  53 */       licenceFile = checkDirForFile(thisDir, filename);
/*  54 */       if (licenceFile == null) {
/*  55 */         parentsLeft--;
/*  56 */         thisDir = thisDir.getParentFile();
/*     */       } else {
/*  58 */         parentsLeft = -1;
/*     */       }
/*  60 */     } while (parentsLeft > 0);
/*  61 */     if (licenceFile != null) {
/*  62 */       this.iStream = licenceFile.toURI().toURL().openStream();
/*     */     }
/*  64 */     loadLicenceFromStream();
/*     */   }
/*     */   
/*     */   private File checkDirForFile(File dir, String filename)
/*     */   {
/*  69 */     File[] fileList = dir.listFiles();
/*  70 */     for (int i = 0; i < fileList.length; i++) {
/*  71 */       if (fileList[i].isDirectory()) {
/*  72 */         File retFile = null;
/*  73 */         retFile = checkDirForFile(fileList[i], filename);
/*  74 */         if (retFile != null) {
/*  75 */           return retFile;
/*     */         }
/*     */       }
/*  78 */       if (fileList[i].getName().compareToIgnoreCase(filename) == 0) {
/*  79 */         System.out.println("Found licence file at " + fileList[i].getAbsolutePath() + ".");
/*  80 */         return fileList[i];
/*     */       }
/*     */     }
/*     */     
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   private void loadLicenceFromStream() throws IOException {
/*  88 */     BufferedReader br = null;
/*  89 */     br = new BufferedReader(new InputStreamReader(this.iStream));
/*     */     
/*  91 */     boolean quit = false;
/*  92 */     String line; while (((line = br.readLine()) != null) && (!quit)) {
/*  93 */       this.licenceText = (this.licenceText + line + "\n");
/*  94 */       if (line.contains(this.stopPhrase.subSequence(0, this.stopPhrase.length()))) {
/*  95 */         quit = true;
/*     */       }
/*  97 */       line = "";
/*     */     }
/*     */   }
/*     */   
/*     */   public void setStream(InputStream is) throws IOException {
/* 102 */     this.iStream = is;
/* 103 */     loadLicenceFromStream();
/*     */   }
/*     */   
/*     */   public void setLicenceText(String newText) {
/* 107 */     this.licenceText = newText;
/*     */   }
/*     */   
/* 110 */   public String getLicenceText() { return this.licenceText; }
/*     */   
/*     */   public String getWebURL()
/*     */   {
/* 114 */     return this.licenceURL.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Mathew\Desktop\jPasswordGenerator.jar!\jPasswordGenerator\licence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */