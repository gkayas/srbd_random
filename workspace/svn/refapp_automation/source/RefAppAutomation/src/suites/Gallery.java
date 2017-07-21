package suites;

import base.TCBase;


public class Gallery extends TCBase {

	public Gallery() {
		super();
		device.removeAlbums();
		device.removeImageFiles();
		device.removeVideoFiles();
	}

	public Gallery(String s) {
		super(s);
	}

	// ========================================================[TestCases]==========================================================

	public void Gallery_001() {		
		device.focus();
		device.restartApp("org.tizen.gallery");
	}

	public void Gallery_002() {
		device.verifyText("No", "items");
	}	
	
	public void Gallery_003() {
		device.pushImageFiles(5000);
		device.restartApp("org.tizen.gallery");
		device.verifyImage("003.png");
	}

	public void Gallery_004() {
		device.verifyText("Albums");
	}

	public void Gallery_005() {
		device.verifyImage("005.png");
	}
	
	public void Gallery_006() {
		device.pressMenu();
		device.verifyText("View as", "Create album", "Reorder", "Delete");
	}

	public void Gallery_007() {
		device.tapText("View as");
	}

	public void Gallery_008() {
		device.verifyText("View as");
	}

	public void Gallery_009() {
		device.tapText("Time");
	}

	public void Gallery_010() {
		device.pressMenu();
		//device.waitForMiliSeconds(2000);
		device.verifyText("View as", "Delete", "Sort b");
	}

	public void Gallery_011() {
		device.tapText("View as");
		device.verifyText("View as");
	}

	public void Gallery_012() {
		device.verifyText("Time");
	}

	public void Gallery_013() {
		device.verifyText("Albums");
	}

	public void Gallery_014() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Delete");
	}

	public void Gallery_015() {
		//device.waitForMiliSeconds(5000);
		device.verifyText("Select item");
	}

	public void Gallery_016() {
		device.verifyText("CANCEL");
	}

	public void Gallery_017() {
		device.verifyText("DONE");
	}

	public void Gallery_018() {
		device.verifyText("Select all");
	}

	public void Gallery_019() {
		device.verifyText("Today");
		//device.verifyText("2016");
	}

	public void Gallery_020() {
		device.verifyImage("020-1.png");
		device.verifyImage("020-2.png");
	}

	public void Gallery_021() {
		device.tapImage("021.png");
	}

	public void Gallery_022() {
		device.verifyImage("022.png");
	}

	public void Gallery_023() {
		device.verifyImage("023.png");
	}

	public void Gallery_024() {
		device.verifyImage("024.png");
	}

	public void Gallery_025() {
		device.pressBack();
		device.pressBack();
		device.pressMenu();
		device.tapText("Sort b");
	}	

	public void Gallery_026() {
		device.verifyText("Sort b");
	}

	public void Gallery_027() {
		device.verifyText("Date (most recent)");
	}

	public void Gallery_028() {
		device.verifyText("Date (oldest)");
	}

	public void Gallery_029() {		
		device.restartApp("org.tizen.gallery");
		device.pressMenu();
		device.tapText("View as");
		//device.waitForMiliSeconds(2000);
		device.tapText(new Double[] {0.0, 0.5, 1.0, 1.0}, "Albums");
	}

	public void Gallery_030() {
		//device.waitForMiliSeconds(2000);
		device.pressMenu();	
		//device.verifyText("View as", "Create album");
	}

	public void Gallery_031() {
		device.tapText("View as");
	}

	public void Gallery_032() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Time");
		device.verifyText("Albums");
	}

	public void Gallery_033() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Create album");
	}

	public void Gallery_034() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Create album");
	}

	public void Gallery_035() {
		device.verifyText("Album");
	}

	public void Gallery_036() {
		device.verifyText("Cancel");
	}

	public void Gallery_037() {
		device.tapText("Save");
	}

	public void Gallery_038() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Select item");
	}

	public void Gallery_039() {
		device.verifyText("Select all");
	}

	public void Gallery_040() {
		device.verifyImage("040-1.png");
		device.verifyImage("040-2.png");
	}

	public void Gallery_041() {
		device.tapImage("041.png");
	}

	public void Gallery_042() {
		device.verifyImage("042.png");
	}

	public void Gallery_043() {
		device.verifyImage("043.png");
	}

	public void Gallery_044() {
		device.verifyImage("044.png");
	}

	public void Gallery_045() {
		device.pressBack();
		device.verifyText("CANCEL");
	}

	public void Gallery_046() {
		device.tapImage("046.png");
	    device.tapText("DONE");
	}

	public void Gallery_047() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Create album");
	}

	public void Gallery_048() {
		device.verifyText("want", "move");
		device.verifyText("the pictures from the original");
		device.verifyText("album");
	}

	public void Gallery_049() {
		device.verifyImage("049.png");
	}

	public void Gallery_050() {
		device.verifyImage("050.png");
	}

	public void Gallery_051() {
		device.verifyImage("051.png");	
	}

	public void Gallery_052() {
		device.pressBack();
		device.pressBack();
		device.pressMenu();
		device.tapText("Reorder");
	}

	public void Gallery_053() {
		device.verifyImage("053.png");
	}

	public void Gallery_054() {
		device.verifyText("Reorder");
	}

	public void Gallery_055() {
		device.verifyImage("055.png");
	}

	public void Gallery_056() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Delete");
	}

	public void Gallery_057() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Select item");
	}

	public void Gallery_058() {
		device.verifyText("CANCEL");
	}

	public void Gallery_059() {
		device.verifyText("DONE");
	}

	public void Gallery_060() {
		device.verifyText("Select all");
	}

	public void Gallery_061() {
		device.verifyImage("061.png");
	}
	
	public void Gallery_062() 
	{
		device.restartApp("org.tizen.gallery");		
		device.pressMenu();
		device.tapText("Create album");
	}

	public void Gallery_063() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Create album");
	}

	public void Gallery_064() {
		device.verifyText("Cancel");
	}
	
	public void Gallery_065() {
		device.verifyText("Album");
	}
	
	public void Gallery_066() {
		device.tapText("Save");
	}
	
	public void Gallery_067() {
		//device.waitForMiliSeconds(2000);		
		device.tapText("Images");
	}
	public void Gallery_068() {
		device.verifyText("Select item");
	}

	public void Gallery_069() {
		device.verifyText("Select all");
	}

	public void Gallery_070() {
		device.verifyImage("070-1.png");
		device.verifyImage("070-2.png");
	}

	public void Gallery_071() {
		device.tapImage("071.png");
	}

	public void Gallery_072() {
		device.verifyImage("072.png");
	}

	public void Gallery_073() {
		device.verifyImage("073.png");
	}

	public void Gallery_074() {
		device.verifyImage("074.png");
	}

	public void Gallery_075() {
		device.pressBack();
		device.verifyText("CANCEL");
	}

	public void Gallery_076() {
		device.tapImage("076.png");
	    device.tapText("DONE");
	}

	public void Gallery_077() {
		//device.waitForMiliSeconds(4000);
		device.verifyText("Create album");
	}

	public void Gallery_078() {
		device.verifyText("want", "move");
		device.verifyText("the pictures from the original");
		device.verifyText("album");
	}

	public void Gallery_079() {
		device.verifyImage("079.png");
	}
	
	public void Gallery_080() {
		device.tapImage("080.png");
	}
	
	public void Gallery_082() {
		device.verifyImage("085.png");
	}
	
	public void Gallery_083() {
		device.pressMenu();
		//device.waitForMiliSeconds(2000);
		device.tapText("Create album");
		//device.waitForMiliSeconds(3000);
		device.tapText("Save");
		device.tapImage(new Double[] {0.0, 0.0, 1.0, 0.5}, 0.90, "076.png");
		//device.waitForMiliSeconds(2000);
		device.tapText("DONE");
		device.tapImage("083.png");
	}
	
	public void Gallery_085() {
		device.verifyImage("085.png");
	}
	
	public void Gallery_086() {
		device.restartApp("org.tizen.gallery");
		device.pressMenu();
		device.tapText("Delete");
	}

	public void Gallery_087() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Select item");
	}
	
	public void Gallery_088() {
		device.verifyText("Select all");
	}
	
	public void Gallery_089() {
		device.verifyImage("090.png");
	}
	
	public void Gallery_090() {
		device.verifyImage("090.png");
	}
	
	public void Gallery_091() {
		device.verifyImage("091.png");
	}
	
	public void Gallery_092() {
		device.verifyText("CANCEL");
	}
	
	public void Gallery_093() {
		device.tapImage("093.png");
		device.tapText("DONE");
	}
	
	public void Gallery_094() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Delete");
	}
	
	public void Gallery_095() {
		device.verifyText("be", "deleted");
	}
	
	public void Gallery_096() {
		device.verifyText("Cancel");
	}
	
	public void Gallery_097() {
		device.verifyText(new Double[] {0.0, 0.9, 1.0, 1.0} , "Delete");
	}
	
	public void Gallery_098() {
		device.restartApp("org.tizen.gallery");
		device.pressMenu();		
		device.tapText("Reorder");
	}
	
	public void Gallery_099() {
		device.verifyText("Reorder");
	}
	
	public void Gallery_100() {
		device.verifyImage("100.png");
	}
	
	public void Gallery_101() {
		device.verifyImage("101.png");
	}
	
	public void Gallery_102() {
		device.restartApp("org.tizen.gallery");
		//device.waitForMiliSeconds(2000);
		device.tapImage("090.png");
	}
	
	public void Gallery_103() {
		device.verifyText("Album");
	}
	
	public void Gallery_104() {
		device.verifyImage("104.png");
	}
	
	public void Gallery_105() {
		device.verifyImage("105-1.png");
		device.verifyImage("105-2.png");
	}
	
	public void Gallery_106() {
		device.pressMenu();
	}
	
	public void Gallery_107() {
		device.tapText("Delete");
	}
	
	public void Gallery_108() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Select item");
	}
	
	public void Gallery_109() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Sort");
	}
	
	public void Gallery_110() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Sort b");
	}
	
	public void Gallery_111() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Copy to album");
	}
	
	public void Gallery_112() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Select item");
	}
	
	public void Gallery_113() {
		device.verifyText("Select all");
	}
	
	public void Gallery_114() {
		device.verifyImage("114-1.png");
		device.verifyImage("114-2.png");
	}
	
	public void Gallery_115() {
		device.tapImage(new Double[] {0.0, 0.0, 0.2, 0.5}, "115.png");
	}
	
	public void Gallery_116() {
		device.verifyImage("116.png");
	}

	public void Gallery_117() {
		device.verifyImage("117.png");
	}

	public void Gallery_118() {
		device.verifyImage("118.png");
	}
	
	public void Gallery_119() {
		device.pressBack();
		device.verifyText("CANCEL");
	}

	public void Gallery_120() {
		device.tapImage("120.png");
	    device.tapText("DONE");
	}
	
	public void Gallery_121() {
		//device.waitForMiliSeconds(3000);
	    device.verifyImage("121.png");
	}
	
	public void Gallery_122() {
	    device.verifyText("Album");
	    //device.verifyText("Album 1");
	}
	
	public void Gallery_123() {
	    device.tapText("Album");
	}
	
	
	public void Gallery_125() {
	    device.verifyImage("125.png");
	}
	
	public void Gallery_124() {
		device.verifyImage("124.png");
	}
	
	public void Gallery_126() {
		device.pressMenu();
		//device.waitForMiliSeconds(2000);
		device.tapText("Copy to album");
		device.tapImage( 0.9, "120.png");
		device.tapText("DONE");
		//device.waitForMiliSeconds(3000);
		device.verifyText("Cancel");
	}
	
	public void Gallery_127() {
	    device.tapText("Create album");
	}
	
	public void Gallery_128() {
	    device.verifyText("Cancel");
	}
	
	public void Gallery_129() {
	    device.tapText("Album 1");
	}
	
	public void Gallery_130() {
	    device.tapText("Save");
	}
	
	public void Gallery_132() {
	    device.verifyImage("125.png");
	}
	
	public void Gallery_131() {
		device.verifyImage("124.png");
	}
		
	public void Gallery_133() {
		device.removeAlbums();
		device.pushImageFiles(5000);
		device.restartApp("org.tizen.gallery");		
		device.tapImage("133-1.png");
		//device.waitForMiliSeconds(3000);
		device.tapImage("133.png");
	}
	
	public void Gallery_134() {
		device.tapOnScreen();
		device.verifyImage("134.png");		
	}
	
	public void Gallery_135() {
		device.tapOnScreen();
		device.verifyImage("135.png");		
	}
	
	public void Gallery_136() {
		device.pressMenu();
	}
	
	public void Gallery_137() {
		device.tapText("Slideshow");
	}
	
	public void Gallery_138() {
		device.tapOnMiddle();
		device.verifyImage("138.png");
	}
	
	public void Gallery_139() {
		device.verifyText("St0P");
	}
	
	public void Gallery_140() {
		device.tapText("Slide interval");
	}
	
	public void Gallery_141() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("1 second");
	}
	
	public void Gallery_142() {
		device.verifyText("3 seconds");
	}
	
	public void Gallery_143() {
		device.verifyText("5 seconds");
	}
	
	public void Gallery_144() {
		device.pressBack();
		device.tapText("Effect");
	}
	
	public void Gallery_145() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Flow");
	}
	
	public void Gallery_146() {
		device.verifyText("Fade");
	}
	
	public void Gallery_147() {
		device.pressBack();
		device.tapText("St0P");
//		device.pressBack();
//		device.tapImage("133.png");
		device.pressMenu();
		//device.waitForMiliSeconds(2000);
		device.tapText("Delete");

	}
	
	public void Gallery_148() {
		device.verifyText("Delete");
	}
	
	public void Gallery_149() {
		device.verifyText("This image will be deleted");
	}
	
	public void Gallery_150() {
		device.verifyText("Cancel");
	}
	
	public void Gallery_151() {
		device.verifyText("Delete");
	}
	
	public void Gallery_152() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Rename");
	}
	
	public void Gallery_153() {
		device.verifyText("Rename");
	}

	public void Gallery_154() {
		device.typeText("22");
		//device.waitForMiliSeconds(2000);
		device.verifyText("2");
	}
	
	public void Gallery_155() {
		device.verifyText("Cancel");
	}
	
	public void Gallery_156() {
		device.verifyText("Rename");
	}
	
	public void Gallery_157() {	
		//device.pushImageFiles(3000);
		device.pushVideoFiles(5000);
		device.restartApp("org.tizen.gallery");
		device.tapImage("157-1.png");
		//device.waitForMiliSeconds(4000);
		device.tapImage("157-2.png");
	}
	
	
	public void Gallery_158() {
		//device.waitForMiliSeconds(3000);
		device.pressMenu();
	}
	
	public void Gallery_159() {
		//device.waitForMiliSeconds(3000);
		device.tapText("Slideshow");
	}
	
	public void Gallery_160() {
		device.tapOnMiddle();
		device.verifyImage("160.png");
	}
	
	public void Gallery_161() {
		device.verifyText("St0P");
	}
	
	public void Gallery_162() {
		device.tapText("Slide interval");
	}
	
	public void Gallery_163() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("1 second");
	}
	
	public void Gallery_164() {
		device.verifyText("3 seconds");
	}
	
	public void Gallery_165() {
		device.verifyText("5 seconds");
	}
	
	public void Gallery_166() {
		device.pressBack();
		device.tapText("Effect");
	}
	
	public void Gallery_167() {
		//device.waitForMiliSeconds(3000);
		device.verifyText("Flow");
	}
	
	public void Gallery_168() {
		device.verifyText("Fade");
	}
	
	public void Gallery_169() {
		device.pressBack();
		device.tapText("St0P");
		device.pressMenu();
		//device.waitForMiliSeconds(3000);
		device.tapText("Delete");

	}
	
	public void Gallery_170() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Delete");
	}
	
	public void Gallery_171() {
		device.verifyText("will be deleted");
	}
	
	public void Gallery_172() {
		device.verifyText("Cancel");
	}
	
	public void Gallery_173() {
		device.verifyText("Delete");
	}
	
	public void Gallery_174() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Rename");
	}
	
	public void Gallery_175() {
		device.verifyText("Rename");
	}

	public void Gallery_176() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("samp|eH265");
	}
	
	public void Gallery_177() {
		device.verifyText("Cancel");
	}
	
	public void Gallery_178() {
		device.verifyText("Rename");
		device.tapText("Cancel");
		
	}
	
	public void Gallery_179() {
		//device.waitForMiliSeconds(2000);
		device.tapImage("179-1.png");
		device.waitForMiliSeconds(4000);
		device.tapOnMiddle();
//		//device.waitForMiliSeconds(1000);
//		device.waitForImage(3000, "179-2.png");
		device.tapImage("179-2.png");			
	}
		
	public void Gallery_180() {
		device.verifyImage("180.png");
	}
	
	public void Gallery_181() {
		device.verifyImage("181.png");
	}
	
	public void Gallery_182() {
		device.verifyImage("182.png");
	}
	
	public void Gallery_183() {
		device.verifyImage("183.png");
	}
	
	public void Gallery_184() {
		device.verifyImage("184.png");
	}
	
	public void Gallery_185() {
		device.verifyImage("185.png");
	}
	
	public void Gallery_186() {
		device.verifyImage("186.png");
	}
	
	public void Gallery_187() {
		device.verifyImage("184.png");
	}
	
	public void Gallery_188() {
		device.verifyImage("185.png");
	}
	
	public void Gallery_189() {
		device.verifyImage("189.png");
	}
	
	public void Gallery_190() {
		device.pressMenu();
	}

	public void Gallery_191() {
		device.verifyText("Share");
	}
	
	public void Gallery_192() {
		device.tapText("Delete");
	}
	
	public void Gallery_193() {
		device.verifyText("Delete");
	}
	
	public void Gallery_194() {
		device.verifyText("will be deleted.");
	}
	
	public void Gallery_195() {
		device.verifyText("Cancel");
	}
	
	public void Gallery_196() {
		device.verifyText("Delete");
	}
	
	public void Gallery_197() {
		device.pressBack();
		device.pressMenu();
		device.verifyText("Play speed");
	}
	
	public void Gallery_198() {
		device.verifyText("Subtitles (CC)");
	}
	
	public void Gallery_199() {
		device.verifyText("Details");
	}
	
	public void Gallery_200() {
		device.restartApp("org.tizen.gallery");
		device.pressMenu();
		//device.waitForMiliSeconds(2000);
		device.tapText("View as");
		//device.waitForMiliSeconds(2000);
		device.tapText("Time");
	}
	
	public void Gallery_201() {
		device.verifyText("Time");
	}
	public void Gallery_202() {
		device.verifyText("Today");
	}
	public void Gallery_203() {
		device.verifyImage("203-1.png");
		device.verifyImage("203-2.png");
	}
	public void Gallery_204() {
		device.tapImage("203-1");
	}
	public void Gallery_205() {
		device.tapOnScreen();
		device.verifyImage("134.png");		
	}
	
	public void Gallery_206() {
		device.tapOnScreen();
		device.verifyImage("135.png");		
	}
	
	public void Gallery_207() {
		device.pressMenu();
	}
	
	public void Gallery_208() {
		device.verifyText("Slideshow");
		device.verifyText("Delete");
		device.verifyText("Rename");
	}
	public void Gallery_209() {
		device.pressBack();
		device.pressBack();
		device.tapImage("157-2.png");
	}
	public void Gallery_210() {
		device.tapOnScreen();
		device.verifyImage("134.png");		
	}
	
	public void Gallery_211() {
		device.tapOnScreen();
		device.verifyImage("135.png");		
	}
	
	public void Gallery_212() {
		device.pressMenu();
	}
	
	public void Gallery_213() {
		device.verifyText("Slideshow");
		device.verifyText("Delete");
		device.verifyText("Rename");
	}
	
	public void Gallery_214() {
		device.pressBack();
		device.pressBack();
		device.pressMenu();
	}
	
	public void Gallery_215() {
		device.tapText("View as");
	}
	
	public void Gallery_216() {
		device.verifyText("Time");
	}
	
	public void Gallery_217() {
		device.verifyText("Albums");
	}
	
	public void Gallery_218() {
		device.pressBack();
		device.pressMenu();
		device.tapText("Delete");
	}
	
	public void Gallery_219() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Select item");
	}
	
	public void Gallery_220() {
		device.verifyText("Select all");
	}
	
	public void Gallery_221() {
		device.verifyImage("221.png");
	}
	
	public void Gallery_222() {
		device.verifyImage("222-1.png");
		device.verifyImage("222-2.png");
	}
	
	public void Gallery_223() {
		device.tapImage("222-1");
	}
	
	public void Gallery_224() {
		device.verifyText("CANCEL");
	}
	
	public void Gallery_225() {
		device.tapText("DONE");
	}
	
	public void Gallery_226() {
		device.verifyImage("226.png");
	}
	
	public void Gallery_227() {
		device.pressMenu();
		device.tapText("Sort b");
	}	

	public void Gallery_228() {
		device.verifyText("Sort b");
	}

	public void Gallery_229() {
		//device.waitForMiliSeconds(2000);
		device.verifyText("Date (most recent)");
	}

	public void Gallery_230() {
		device.verifyText("Date (oldest)");
	}

}
