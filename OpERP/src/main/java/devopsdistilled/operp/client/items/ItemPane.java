package devopsdistilled.operp.client.items;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import devopsdistilled.operp.server.data.entity.items.Brand;
import devopsdistilled.operp.server.data.entity.items.Product;

public class ItemPane implements ItemModelObserver {

	private final ItemModel model;
	private final ItemPaneController controller;

	private final JDialog dialog;
	private final JPanel pane;
	private final JComponent owner;
	private final JTextField textField;
	private final JTextField textField_3;
	private final JComboBox<Brand> comboBrands;
	private final JComboBox<Product> comboProducts;

	public ItemPane(final JComponent owner, ItemModel itemModel,
			ItemPaneController itemPaneController) {

		pane = new JPanel();
		pane.setLayout(new MigLayout("", "[][][grow][]", "[][][][][]"));

		JLabel lblItemId = new JLabel("Item ID");
		pane.add(lblItemId, "cell 0 0,alignx trailing");

		textField = new JTextField();
		pane.add(textField, "cell 2 0,growx");
		textField.setColumns(10);

		JLabel lblProductName = new JLabel("Product Name");
		pane.add(lblProductName, "cell 0 1,alignx trailing");

		comboProducts = new JComboBox<Product>();
		pane.add(comboProducts, "cell 2 1,growx");

		JButton btnNewProduct = new JButton("New Product");
		pane.add(btnNewProduct, "cell 3 1");

		JLabel lblBrandName = new JLabel("Brand Name");
		pane.add(lblBrandName, "cell 0 2,alignx trailing");

		comboBrands = new JComboBox<Brand>();
		pane.add(comboBrands, "cell 2 2,growx");

		JButton btnNewBrand = new JButton("New Brand");
		btnNewBrand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		pane.add(btnNewBrand, "cell 3 2");

		JLabel lblPrice = new JLabel("Price");
		pane.add(lblPrice, "cell 0 3,alignx trailing");

		textField_3 = new JTextField();
		pane.add(textField_3, "cell 2 3,growx");
		textField_3.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getDialog().setVisible(false);
			}
		});
		pane.add(btnCancel, "flowx,cell 2 4");
		JButton btnSave = new JButton("Save");
		pane.add(btnSave, "cell 2 4");

		dialog = new JDialog();

		this.owner = owner;
		this.model = itemModel;
		model.registerObserver(this);
		this.controller = itemPaneController;
		controller.populateData();

	}

	public JComponent getPane() {
		return pane;
	}

	public JDialog getDialog() {
		dialog.getContentPane().add(getPane());
		dialog.setSize(640, 800);
		dialog.setLocationRelativeTo(owner);
		dialog.setVisible(true);
		return dialog;
	}

	@Override
	public void updateAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProducts() {
		List<Product> products = model.getProducts();

		Product[] productsArray = (Product[]) products.toArray();
		ComboBoxModel<Product> comboModel = new DefaultComboBoxModel<>(
				productsArray);
		comboProducts.setModel(comboModel);
	}

	@Override
	public void updateBrands() {
		List<Brand> brands = model.getBrands();
		Brand[] brandsArray = (Brand[]) brands.toArray();
		ComboBoxModel<Brand> comboModel = new DefaultComboBoxModel<>(
				brandsArray);
		comboBrands.setModel(comboModel);
	}
}
