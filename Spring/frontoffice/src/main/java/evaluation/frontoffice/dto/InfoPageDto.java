package evaluation.frontoffice.dto;

public class InfoPageDto {
    int nombrePage;
	int page;
	int pageSize;
	String colonne;
	String tri;
	public InfoPageDto() {
		
	}
	
	

	public InfoPageDto(int nombrePage, int page, int pageSize, String colonne, String tri) {
		super();
		this.nombrePage = nombrePage;
		this.page = page;
		this.pageSize = pageSize;
		this.colonne = colonne;
		this.tri = tri;
	}



	public int getNombrePage() {
		return nombrePage;
	}

	public void setNombrePage(int nombrePage) {
		this.nombrePage = nombrePage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getColonne() {
		return colonne;
	}

	public void setColonne(String colonne) {
		this.colonne = colonne;
	}

	public String getTri() {
		return tri;
	}

	public void setTri(String tri) {
		this.tri = tri;
	}
}
