/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.PilihanBundle;

/**
 *
 * @author Victus
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class BundlePUBG extends JFrame {

    private JFrame backTo;

    private String currencyName = "UC";
    private ImageIcon currencyIcon;
    private ImageIcon logoFooter;

    public BundlePUBG(JFrame backTo) {
        this.backTo = backTo;

        setTitle("PUBG - Bundling");
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color ORANGE = new Color(0xFF8C1A);

        // load icons
        currencyIcon = loadIcon("/Kinopedia/model/IMAGESS/UC-PUBGs.png", 22, 22);
        logoFooter   = loadIcon("/Kinopedia/model/IMAGESS/LogoKinopedia.png", 70, 70);

        // root
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        // top bar
        JButton backBtn = new JButton("< Kembali");
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backBtn.setHorizontalAlignment(SwingConstants.LEFT);
        backBtn.addActionListener(e -> {
            dispose();
            if (backTo != null) backTo.setVisible(true);
        });

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.add(backBtn, BorderLayout.WEST);
        root.add(top, BorderLayout.NORTH);

        // scroll content
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(10, 0, 0, 0));

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        root.add(scroll, BorderLayout.CENTER);

        // NUMBER ID
        JLabel idLabel = new JLabel("NUMBER ID");
        idLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        idLabel.setForeground(Color.DARK_GRAY);
        idLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(idLabel);
        content.add(Box.createVerticalStrut(6));

        JTextField idField = new JTextField("225180606 (2554)");
        idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        idField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(ORANGE, 2, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
        idField.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(idField);

        content.add(Box.createVerticalStrut(10));

        // ACCOUNT NAME
        JLabel accLabel = new JLabel("ACCOUNT NAME");
        accLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        accLabel.setForeground(Color.DARK_GRAY);
        accLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(accLabel);

        content.add(Box.createVerticalStrut(6));

        JTextField accNameField = new JTextField("KelvinAngjaya123");
        accNameField.setEditable(false);
        accNameField.setBackground(new Color(0xD9D9D9));
        accNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        accNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(ORANGE, 2, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
        accNameField.setHorizontalAlignment(JTextField.CENTER);
        accNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(accNameField);

        content.add(Box.createVerticalStrut(14));

        // tag "1. Bundling"
        JPanel tagPanel = new JPanel(new BorderLayout());
        tagPanel.setBackground(Color.WHITE);
        tagPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(ORANGE, 2, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
        tagPanel.setMaximumSize(new Dimension(220, 48));
        tagPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tagText = new JLabel("1. Bundling", SwingConstants.CENTER);
        tagText.setFont(new Font("SansSerif", Font.BOLD, 20));
        tagPanel.add(tagText, BorderLayout.CENTER);

        content.add(tagPanel);
        content.add(Box.createVerticalStrut(10));

        // orange box + grid
        JPanel orangeBox = new JPanel(new BorderLayout());
        orangeBox.setBackground(ORANGE);
        orangeBox.setBorder(new EmptyBorder(14, 14, 14, 14));
        orangeBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel grid = new JPanel(new GridLayout(0, 2, 12, 12));
        grid.setOpaque(false);
        orangeBox.add(grid, BorderLayout.CENTER);

        addCard(grid, "10 " + currencyName, "Rp 5.000");
        addCard(grid, "50 " + currencyName, "Rp 22.000");
        addCard(grid, "75 " + currencyName, "Rp 32.000");
        addCard(grid, "100 " + currencyName, "Rp 49.000");
        addCard(grid, "200 " + currencyName, "Rp 80.000");
        addCard(grid, "400 " + currencyName, "Rp 149.000");
        addCard(grid, "1000 " + currencyName, "Rp 267.000");
        addCard(grid, "2500 " + currencyName, "Rp 525.000");

        content.add(orangeBox);
        content.add(Box.createVerticalStrut(18));

        // footer fixed
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(new EmptyBorder(10, 0, 0, 0));
        root.add(footer, BorderLayout.SOUTH);

        JButton payBtn = new JButton("Pilih Metode Pembayaran");
        payBtn.setBackground(ORANGE);
        payBtn.setForeground(Color.BLACK);
        payBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        payBtn.setFocusPainted(false);
        payBtn.setOpaque(true);
        payBtn.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        JPanel payWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        payWrap.setBackground(Color.WHITE);
        payWrap.add(payBtn);
        footer.add(payWrap);

        footer.add(Box.createVerticalStrut(14)); // jarak logo

        JLabel logoLabel = (logoFooter != null) ? new JLabel(logoFooter) : new JLabel("Kinopedia");
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.add(logoLabel);
    }

    private ImageIcon loadIcon(String path, int w, int h) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Icon not found: " + path);
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void addCard(JPanel grid, String title, String price) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(10, 12, 10, 12));

        JLabel iconLabel = (currencyIcon != null) ? new JLabel(currencyIcon) : new JLabel("?");
        iconLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel t = new JLabel(title);
        t.setFont(new Font("SansSerif", Font.BOLD, 12));
        t.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel p = new JLabel(price);
        p.setFont(new Font("SansSerif", Font.PLAIN, 12));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(t);
        card.add(Box.createVerticalStrut(2));
        card.add(p);

        grid.add(card);
    }
}