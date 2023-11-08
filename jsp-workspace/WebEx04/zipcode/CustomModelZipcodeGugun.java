import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class CustomModelZipcodeGugun extends DefaultComboBoxModel<String> {
	
	private ArrayList<String> datas;

	public CustomModelZipcodeGugun(String sido) {
		ZipcodeDAO dao = new ZipcodeDAO();
		datas = dao.listGugun(sido);
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
