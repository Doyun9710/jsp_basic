import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class CustomModelZipcodeSido extends DefaultComboBoxModel<String> {
	
	private ArrayList<String> datas;

	public CustomModelZipcodeSido() {
		ZipcodeDAO dao = new ZipcodeDAO();
		datas = dao.listSido();
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
