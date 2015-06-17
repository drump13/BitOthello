package ai;

public enum Pattern {
	
	
	DIAG4_LU(1161999622361579520L,"pattern/diag4lu.txt"),//DIAG4_RU(577588855528488960L,"pattern/diag4ru.txt"),DIAG4_RD(16909320L,"pattern/diag4rd.txt"),DIAG4_LD(2151686160L,"pattern/diag4ld.txt"),
	DIAG5_LU(580999813328273408L,"pattern/diag5lu.txt"),//DIAG5_RU(1155177711073755136L,"pattern/diag5ru.txt"),DIAG5_RD(4328785936L,"pattern/diag5rd.txt"),DIAG5_LD(550831656968L,"pattern/diag5ld.txt"),
	DIAG6_LU(290499906672525312L,"pattern/diag6lu.txt"),//DIAG6_RU(2310355422147575808L,"pattern/diag6ru.txt"),DIAG6_RD(1108169199648L,"pattern/diag6rd.txt"),DIAG6_LD(141012904183812L,"pattern/diag6ld.txt"),
	DIAG7_LU(145249953336295424L,"pattern/diag7lu.txt"),//DIAG7_RU(4620710844295151872L,"pattern/diag7ru.txt"),DIAG7_RD(283691315109952L,"pattern/diag7rd.txt"),DIAG7_LD(36099303471055874L,"pattern/diag7ld.txt"),
	HORVERT2_U(71776119061217280L,"pattern/horvert2u.txt"),//HORVERT2_R(144680345676153346L,"pattern/horvert2r.txt"),HORVERT2_D(65280L,"pattern/horvert2d.txt"),HORVERT2_L(4629771061636907072L,"pattern/horvert2l.txt"),
	HORVERT3_U(280375465082880L,"pattern/horvert3u.txt"),//HORVERT3_R(289360691352306692L,"pattern/horvert3r.txt"),HORVERT3_D(16711680L,"pattern/horvert3d.txt"),HORVERT3_L(2314885530818453536L,"pattern/horvert3l.txt"),
	HORVERT4_U(1095216660480L,"pattern/horvert4u.txt"),//HORVERT4_R(578721382704613384L,"pattern/horvert4r.txt"),HORVERT4_D(4278190080L,"pattern/horvert4d.txt"),HORVERT4_L(1157442765409226768L,"pattern/horvert4l.txt"),
	EDGE_LU(-126100789566373888L,"pattern/edgelu.txt"),//EDGE_RU(72903122791497984L,"pattern/edgeru.txt"),EDGE_RD(639L,"pattern/edgerd.txt"),EDGE_LD(36170086419054720L,"pattern/edgeld.txt"),
	CORNER_LU(-2242581508197974016L,"pattern/cornerlu.txt");//,CORNER_RU(506376781637353472L,"pattern/cornerru.txt"),CORNER_RD(198407L,"pattern/cornerrd.txt"),CORNER_LD(12640480L,"pattern/cornerld.txt");
	
	private long pattern;
	private String filename;
	
	Pattern(long pattern,String filename){
		this.pattern = pattern;
		this.filename = filename;
	}
	
	public long getBoard(){
		return pattern;
	}
	
	public String getFileName(){
		return filename;
	}
	
	
}
