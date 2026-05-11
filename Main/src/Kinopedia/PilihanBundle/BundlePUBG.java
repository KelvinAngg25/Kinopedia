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
        currencyIcon = loadIcon("/Kinopedia/model/IMAGESS/UC-PUBGs.png", 18, 18);
        logoFooter   = loadIcon("/Kinopedia/model/IMAGESS/LogoKinopedia.png", 50, 50);

        // root
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(18, 18, 18, 18));
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
        content.setBorder(new EmptyBorder(8, 12, 0, 12));

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
        idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        idField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(ORANGE, 2, true),
                new EmptyBorder(8, 12, 8, 12)
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
        accNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        accNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(ORANGE, 2, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        accNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(accNameField);
        content.add(Box.createVerticalStrut(12));

        // tag "1. Bundling"
        JPanel tagPanel = new JPanel(new BorderLayout());
        tagPanel.setBackground(Color.WHITE);
        tagPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(ORANGE, 2, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
        tagPanel.setMaximumSize(new Dimension(160, 32));
        tagPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tagText = new JLabel("1. Bundling", SwingConstants.CENTER);
        tagText.setFont(new Font("SansSerif", Font.BOLD, 12));
        tagPanel.add(tagText, BorderLayout.CENTER);

        content.add(tagPanel);
        content.add(Box.createVerticalStrut(8));

        // orange box + grid
        JPanel orangeBox = new JPanel(new BorderLayout());
        orangeBox.setBackground(ORANGE);
        orangeBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        orangeBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel grid = new JPanel(new GridLayout(0, 2, 8, 8));
        grid.setOpaque(false);
        orangeBox.add(grid, BorderLayout.CENTER);

        addCard(grid, "60 " + currencyName, "Rp 17.200");
        addCard(grid, "325 " + currencyName, "Rp 82.500");
        addCard(grid, "821 " + currencyName, "Rp 211.000");
        addCard(grid, "1800 " + currencyName, "Rp 400.000");
        addCard(grid, "3850 " + currencyName, "Rp 795.000");
        addCard(grid, "8100 " + currencyName, "Rp 1.580.000");
        addCard(grid, "16200 " + currencyName, "Rp 3.000.000");
        addCard(grid, "40500 " + currencyName, "Rp 7.900.000");

        content.add(orangeBox);
        content.add(Box.createVerticalStrut(10));

        // footer fixed
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(new EmptyBorder(10, 0, 0, 0));
        root.add(footer, BorderLayout.SOUTH);

        JButton payBtn = new JButton("Pilih Metode Pembayaran");
        payBtn.setBackground(ORANGE);
        payBtn.setForeground(Color.BLACK);
        payBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        payBtn.setFocusPainted(false);
        payBtn.setOpaque(true);
        payBtn.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JPanel payWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        payWrap.setBackground(Color.WHITE);
        payWrap.add(payBtn);
        footer.add(payWrap);

        footer.add(Box.createVerticalStrut(14));

        JLabel logoLabel = (logoFooter != null) ? new JLabel(logoFooter) : new JLabel("Logo");
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.add(logoLabel);
    }

    private ImageIcon loadIcon(String path, int w, int h) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Icon not found: " + path);
            return null;
        }
        Image img = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void addCard(JPanel grid, String title, String price) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(6, 8, 6, 8));

        JLabel iconLabel = (currencyIcon != null) ? new JLabel(currencyIcon) : new JLabel("?");
        iconLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel t = new JLabel(title);
        t.setFont(new Font("SansSerif", Font.BOLD, 10));
        t.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel p = new JLabel(price);
        p.setFont(new Font("SansSerif", Font.PLAIN, 10));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(2));
        card.add(t);
        card.add(Box.createVerticalStrut(1));
        card.add(p);

        grid.add(card);
    }
}