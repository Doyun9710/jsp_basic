import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class CustomModelZipcodeDong extends DefaultComboBoxModel<String> {
	
	private ArrayList<String> datas;

	public CustomModelZipcodeDong(String sido, String gugun) {
		ZipcodeDAO dao = new ZipcodeDAO();
		datas = dao.listDong(sido, gugun);
	}
	
	@Override
	public int getSize() {
		return datas.size();
	}
	
	@Override
	public String getElementAt(int index) {
		return datas.get(index);
	}
}
