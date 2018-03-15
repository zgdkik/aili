package org.hbhk.aili.client.core.widget.itservice.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.util.WindowUtil;
import org.hbhk.aili.client.core.component.focuspolicy.annotation.FocusSeq;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;
import org.jdesktop.swingx.JXTable;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * 
 * 上传图片到IT服务台面板
 */
public class UploadITServicePanel extends JPanel {

	private static final int TWO = 2;

	private static final int EIGHTY = 80;

	private static final String DEFAULTGROW = "default:grow";

	private static final int FILESIZE = 300;

	private static final int K = 1024;
	
	//文件名称的长度
	private static final int NAME_LENGTH = 30;
	
	//上传图片的数量
	private static final int UPLOAD_NUM = 3;

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 8842939049457465320L;
	
	/**
	 * 日志类
	 */
	private static final Log LOG = LogFactory.getLog(UploadITServicePanel.class);

	/**
	 * 国际化
	 */
	private static II18n i18n = I18nManager.getI18n(UploadITServicePanel.class);

	/**
	 * 凭证显示表格
	 */
	private JXTable table;

	/**
	 * 凭证增加按钮
	 */
	@FocusSeq(seq=3)
	private JButton btnAdd = new JButton(i18n.get("pickup.changing.add"));

	/**
	 * 凭证删除按钮
	 */
	@FocusSeq(seq=4)
	private JButton btnDelete = new JButton(i18n.get("pickup.changing.delete"));
	
	/**
	 * 上传凭证列
	 */
	@FocusSeq(seq=1)
	private ButtonColumn uploadColumn;
	
	/**
	 * 查看凭证列
	 */
	@FocusSeq(seq=2)
	private ButtonColumn viewColumn;

	/**
	 * 
	 * 构造方法
	 */
	public UploadITServicePanel() {
		init();
		createListener();
	}

	/**
	 * 
	 * 创建按钮监听
	 */
	private void createListener() {
		uploadColumn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 选中的凭证对象
				UploadPictureDto dto = getSelectedDto();

				//弹出图片选择器
				JImageChooser dialog = new JImageChooser();
				int option = dialog.showOpenDialog(ApplicationContext.getApplication().getWorkbench().getFrame());
				if (JImageChooser.APPROVE_OPTION == option) {
					//获取选中的图片文件
					File selectedFile = dialog.getSelectedFile();
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(selectedFile);						
						//读取文件大小
						int length = fis.available();
						String fileName = selectedFile.getName();
						//限制文件大小不能超过300K
						if (length > FILESIZE * K) {
							MsgBox.showError(i18n.get("pickup.changing.uploadLimit"));
						} else if(fileName.length()>NAME_LENGTH){
							//文件名称不能超过30个字符
							MsgBox.showError(i18n.get("pickup.itservice.filename.length.alert"));
						} else {
							byte[] bytes = new byte[length];
							fis.read(bytes);
							//设置文件类型 JPG PNG等
							int index = fileName.lastIndexOf(".");
							if(index >= 0){
								dto.setProofType(fileName.substring(index+1).toUpperCase());
							}else{
								dto.setProofType(null);
							}
							//默认文件不会随上传的文件覆盖文件名
//								if (!dto.getIsDefault()) {
//									if(index >= 0){
//										dto.setProofName(fileName.substring(0, index));
//									}else{
									dto.setProofName(fileName);
//									}
//								}
							//未加密字节码
							dto.setProofBytes(byte2ToString(bytes));
							//加密字节码
							BASE64Encoder encoder = new BASE64Encoder();
							dto.setUploadPath(encoder.encode(bytes));
							MsgBox.showInfo(i18n.get("pickup.changing.uploadSuccess", dto.getProofName()));													
						}
					} catch (FileNotFoundException e1) {
						LOG.error(e1.getMessage(),e1);
					} catch (IOException e1) {
						LOG.error(e1.getMessage(),e1);
					} finally {
						if(fis != null){
							try {
								fis.close();
							} catch (IOException e1) {
								LOG.error(e1);
							}
						}
					}
				}
			}
		});
		
		
		
		viewColumn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 选中的凭证对象
				UploadPictureDto dto = getSelectedDto();
				//图片尚未上传
				if (StringUtils.isEmpty(dto.getUploadPath())) {
					MsgBox.showInfo(i18n.get("pickup.changing.mustUploadVoucher"));
				} else {
					BASE64Decoder decoder = new BASE64Decoder();
					ByteArrayInputStream bais = null;
					try {
						//字符串解密
						byte[] bytes = decoder.decodeBuffer(dto.getUploadPath());
						bais = new ByteArrayInputStream(bytes);
						//在内存中构造缓冲图片
						BufferedImage bufferImg = ImageIO.read(bais);
						//通过预览对话框弹出显示
						JImageViewDialog dialog = new JImageViewDialog(bufferImg);
						WindowUtil.centerAndShow(dialog);
					} catch (IOException e1) {
						LOG.error(e1.getMessage(),e1);
					} finally{
						if(bais != null){
							try {
								bais.close();
							} catch (IOException e1) {
								LOG.error(e1.getMessage(),e1);
							}
						}
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取表格数据模型
				UploadVoucherTableModel uploadVoucherTableModel = ((UploadVoucherTableModel) table.getModel());
				List<UploadPictureDto> uploadVoucherList = uploadVoucherTableModel.getData();
				if(uploadVoucherList!=null && uploadVoucherList.size()>=UPLOAD_NUM){
					MsgBox.showError(i18n.get("pickup.itservice.upload.num.alert"));
				}else{
					//增加凭证对象
					uploadVoucherList.add(new UploadPictureDto());
					uploadVoucherTableModel.fireTableDataChanged();
				}				
			}
		});

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//获取表格数据模型
				UploadVoucherTableModel uploadVoucherTableModel = ((UploadVoucherTableModel) table.getModel());
				List<UploadPictureDto> uploadVoucherList = uploadVoucherTableModel.getData();
				int row = table.getSelectedRow();
				if (row >= 0) {
					//获取选中的凭证对象
					UploadPictureDto vo = uploadVoucherList.get(row);
					//默认凭证不允许删除
					if (vo.getIsDefault()) {
						MsgBox.showError(i18n.get("pickup.changing.voucherNoDelete"));
					} else {
						//删除选中行 
						uploadVoucherList.remove(vo);
						uploadVoucherTableModel.fireTableDataChanged();
					}
				}
			}
		});
	}
	
	/**
	 * 二进制转字符串
	 */
	public static String byte2ToString(byte[] bytes){
		StringBuffer sb = new StringBuffer();		
		String stmp = "";
		for (int n = 0; n < bytes.length; n++) {
			stmp = Integer.toHexString(bytes[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 布局初始化
	 */
	private void init() {
		setBorder(new JDTitledBorder(i18n.get("pickup.changing.itserviceErrorHandler")));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode(DEFAULTGROW),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("37dlu:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 1, 5, 1, fill, fill");

		table = new JXTable(){
			private static final long serialVersionUID = 6865880807055236003L;

			@Override
			public boolean isCellEditable(int row, int column) {
			    if(column == 0){
			    	return false;
			    }else{
			    	return getModel().isCellEditable(
			    			convertRowIndexToModel(row),
			    			convertColumnIndexToModel(column));
			    }
			}
		};
		table.setTableHeader(null);
		scrollPane.setViewportView(table);
		
		add(btnAdd, "4, 3");

		add(btnDelete, "6, 3");

		table.setModel(new UploadVoucherTableModel());
		
		// 添加Button样式
		uploadColumn = TableFactory.createButtonColumn(table, 1, EIGHTY);
		
		viewColumn = TableFactory.createButtonColumn(table, TWO, EIGHTY);
		
		TableFactory.createRowColumn(table);
		
		//初始化控件
		initData();
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		List<UploadPictureDto> uploadVoucherList = getUploadVoucherDtos();
		if(uploadVoucherList == null)
			return;
		setUploadVoucherList(uploadVoucherList);
	}
	
	/**
	 * 获取初始化dto
	 */
	protected List<UploadPictureDto> getUploadVoucherDtos() {
		List<UploadPictureDto> uploadVoucherList = new ArrayList<UploadPictureDto>();
		UploadPictureDto vo = new UploadPictureDto();
		vo.setProofName("");
		vo.setIsDefault(true);
		uploadVoucherList.add(vo);
		return uploadVoucherList;
	}

	/**
	 * 
	 * 获取表格选中行
	 */
	protected UploadPictureDto getSelectedDto() {
		int row = table.getSelectedRow();
		return getTableData().get(row);
	}

	/**
	 * 
	 * 获取表格数据
	 * @author WangQianJin
	 * @date 2013-11-05
	 */
	public List<UploadPictureDto> getTableData() {
		UploadVoucherTableModel uploadVoucherTableModel = ((UploadVoucherTableModel) table.getModel());
		return uploadVoucherTableModel.getData();
	}

	/**
	 * 
	 * 表格设置数据
	 * @author WangQianJin
	 * @date 2013-11-05
	 */
	public void setUploadVoucherList(List<UploadPictureDto> uploadVoucherList) {
		UploadVoucherTableModel uploadVoucherTableModel = ((UploadVoucherTableModel) table.getModel());
		// 刷新表格数据
		uploadVoucherTableModel.setData(uploadVoucherList);
		uploadVoucherTableModel.fireTableDataChanged();
	}
	
	/**
	 * 
	 * 获取表格数据
	 */
	public List<UploadPictureDto> getUploadVoucherList() {
		UploadVoucherTableModel uploadVoucherTableModel = ((UploadVoucherTableModel) table.getModel());
		return uploadVoucherTableModel.getData();
	}

	/**
	 * 
	 * 上传凭证数据模型
	 */
	public class UploadVoucherTableModel extends DefaultTableModel {

		private static final int ZERO = 0;

		private static final int ONE = 1;

		/**
		 * 序列化
		 */
		private static final long serialVersionUID = 4758119136571312332L;

		/**
		 * 表格数据
		 */
		private List<UploadPictureDto> uploadVoucherList;

		/**
		 * 
		 * 获取表格数据
		 */
		public List<UploadPictureDto> getData() {
			return uploadVoucherList;
		}

		/**
		 * 
		 * 设置表格数据
		 */
		public void setData(List<UploadPictureDto> uploadVoucherList) {
			this.uploadVoucherList = uploadVoucherList;
		}

		@Override
		public void setValueAt(Object aValue, int row, int column) {
			 if (column == 0) {
				 uploadVoucherList.get(row).setProofName((String) aValue);
			 }
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return column > 0 || (column == 0 && !uploadVoucherList.get(row).getIsDefault());
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public int getRowCount() {
			return uploadVoucherList == null ? 0 : uploadVoucherList.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			switch (column) {
			case ZERO:
				return uploadVoucherList.get(row).getProofName();
			case ONE:
				return i18n.get("pickup.changing.upload");
			case TWO:
				return i18n.get("pickup.changing.view");
			default:
				return super.getValueAt(row, column);
			}
		}
	}

	/**
	 * 
	 * 添加按钮
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * 
	 * 删除按钮
	 */
	public JButton getBtnDelete() {
		return btnDelete;
	}

	/**
	 * 凭证表格
	 */
	public JXTable getTable() {
		return table;
	}

}
