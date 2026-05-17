package Kinopedia.model.Seller;

import javax.swing.*;
import java.awt.*;

public class Seller extends JFrame {

    private JPanel mainPanel;

    public Seller() {

        setTitle("Menu Seller");
        setSize(375, 812);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponent();

        setVisible(true);
    }

    private void initComponent() {

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 245, 245));

        buatTitle();
        buatTagihanCard();
        buatSaldoCard();
        buatLogoutButton();
        buatLogo();

        add(mainPanel);
    }

    private void buatTitle() {

        JLabel title = new JLabel("Menu Seller");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBounds(25, 70, 200, 25);

        JLabel subtitle = new JLabel("Kelola tagihan dan saldo");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(25, 95, 200, 20);

        mainPanel.add(title);
        mainPanel.add(subtitle);
    }

    private void buatTagihanCard() {

        RoundedPanel tagihanCard = new RoundedPanel(
                new Color(245, 245, 245),
                new Color(255, 145, 0),
                22
        );

        tagihanCard.setLayout(null);
        tagihanCard.setBounds(18, 140, 320, 245);

        RoundedPanel iconBox = new RoundedPanel(
                new Color(255, 145, 0),
                null,
                16
        );

        iconBox.setBounds(18, 16, 50, 50);
        iconBox.setLayout(new GridBagLayout());

        JLabel bag = new JLabel("▢");
        bag.setFont(new Font("SansSerif", Font.BOLD, 24));

        iconBox.add(bag);

        JLabel tagihan = new JLabel("Tagihan");
        tagihan.setFont(new Font("SansSerif", Font.BOLD, 16));
        tagihan.setBounds(82, 18, 100, 20);

        JLabel desc1 = new JLabel("Lihat dan kelola semua tagihan");
        desc1.setFont(new Font("SansSerif", Font.PLAIN, 10));
        desc1.setForeground(Color.GRAY);
        desc1.setBounds(82, 40, 200, 15);

        JLabel desc2 = new JLabel("top up dari buyer.");
        desc2.setFont(new Font("SansSerif", Font.PLAIN, 10));
        desc2.setForeground(Color.GRAY);
        desc2.setBounds(82, 56, 120, 15);

        JPanel redCard = createStatusCard(
                new Color(247, 106, 106),
                new Color(255, 70, 70),
                "◔",
                "Belum di top-up",
                "Tagihan yang belum di proses",
                true
        );

        redCard.setBounds(18, 92, 284, 62);

        JPanel greenCard = createStatusCard(
                new Color(131, 184, 123),
                new Color(83, 170, 60),
                "✓",
                "Sudah di top-up",
                "Tagihan yang sudah di proses",
                false
        );

        greenCard.setBounds(18, 168, 284, 62);

        tagihanCard.add(iconBox);
        tagihanCard.add(tagihan);
        tagihanCard.add(desc1);
        tagihanCard.add(desc2);

        tagihanCard.add(redCard);
        tagihanCard.add(greenCard);

        mainPanel.add(tagihanCard);
    }

    private void buatSaldoCard() {

        RoundedPanel saldoCard = new RoundedPanel(
                new Color(245, 245, 245),
                new Color(255, 145, 0),
                22
        );

        saldoCard.setLayout(null);
        saldoCard.setBounds(18, 402, 320, 225);

        RoundedPanel dollarBox = new RoundedPanel(
                new Color(255, 145, 0),
                null,
                14
        );

        dollarBox.setBounds(18, 20, 42, 42);
        dollarBox.setLayout(new GridBagLayout());

        JLabel dollar = new JLabel("$");
        dollar.setForeground(Color.WHITE);
        dollar.setFont(new Font("SansSerif", Font.BOLD, 15));

        dollarBox.add(dollar);

        JLabel saldo = new JLabel("Saldo");
        saldo.setFont(new Font("SansSerif", Font.BOLD, 16));
        saldo.setBounds(72, 20, 100, 20);

        JLabel saldoDesc = new JLabel("Informasi saldo saat ini");
        saldoDesc.setFont(new Font("SansSerif", Font.PLAIN, 11));
        saldoDesc.setForeground(Color.GRAY);
        saldoDesc.setBounds(72, 42, 150, 15);

        RoundedPanel orangeBox = new RoundedPanel(
                new Color(255, 140, 20),
                null,
                16
        );

        orangeBox.setBounds(18, 95, 284, 108);
        orangeBox.setLayout(new GridBagLayout());

        JLabel amount = new JLabel("Rp 100.000");
        amount.setFont(new Font("SansSerif", Font.BOLD, 28));
        amount.setForeground(new Color(90, 50, 0));

        orangeBox.add(amount);

        saldoCard.add(dollarBox);
        saldoCard.add(saldo);
        saldoCard.add(saldoDesc);
        saldoCard.add(orangeBox);

        mainPanel.add(saldoCard);
    }

    private void buatLogoutButton() {

        RoundedButton logout = new RoundedButton("Log Out");

        logout.setBounds(18, 655, 320, 42);
        logout.setBackground(new Color(255, 140, 20));
        logout.setForeground(Color.BLACK);
        logout.setFont(new Font("SansSerif", Font.BOLD, 16));
        logout.setFocusPainted(false);
        logout.setBorderPainted(false);

        mainPanel.add(logout);
    }

    private void buatLogo() {

        JLabel logo = new JLabel("K");
        logo.setFont(new Font("SansSerif", Font.BOLD, 34));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setBounds(160, 715, 40, 40);

        mainPanel.add(logo);
    }

    private JPanel createStatusCard(
            Color bgColor,
            Color iconColor,
            String icon,
            String title,
            String desc,
            boolean redCircle
    ) {

        RoundedPanel panel = new RoundedPanel(bgColor, null, 14);
        panel.setLayout(null);

        RoundedPanel iconPanel = new RoundedPanel(iconColor, null, 12);
        iconPanel.setBounds(12, 11, 34, 34);
        iconPanel.setLayout(new GridBagLayout());

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

        iconPanel.add(iconLabel);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        titleLabel.setBounds(58, 13, 150, 15);

        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 9));
        descLabel.setForeground(new Color(60, 60, 60));
        descLabel.setBounds(58, 30, 180, 12);

        Color warnaCircle;

        if (redCircle) {
            warnaCircle = new Color(214, 67, 67);
        } else {
            warnaCircle = new Color(75, 135, 45);
        }

        RoundedPanel number = new RoundedPanel(
                warnaCircle,
                null,
                20
        );

        number.setBounds(218, 15, 24, 24);
        number.setLayout(new GridBagLayout());

        JLabel num = new JLabel("6");
        num.setFont(new Font("SansSerif", Font.BOLD, 10));

        number.add(num);

        JLabel arrow = new JLabel("›");
        arrow.setFont(new Font("SansSerif", Font.BOLD, 28));
        arrow.setBounds(255, 9, 20, 35);

        panel.add(iconPanel);
        panel.add(titleLabel);
        panel.add(descLabel);
        panel.add(number);
        panel.add(arrow);

        return panel;
    }

    class RoundedPanel extends JPanel {

        private Color bg;
        private Color border;
        private int radius;

        public RoundedPanel(Color bg, Color border, int radius) {

            this.bg = bg;
            this.border = border;
            this.radius = radius;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(bg);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );

            if (border != null) {

                g2.setColor(border);

                g2.setStroke(new BasicStroke(2));

                g2.drawRoundRect(
                        0,
                        0,
                        getWidth() - 1,
                        getHeight() - 1,
                        radius,
                        radius
                );
            }

            g2.dispose();

            super.paintComponent(g);
        }
    }

    class RoundedButton extends JButton {

        public RoundedButton(String text) {

            super(text);

            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(getBackground());

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    16,
                    16
            );

            super.paintComponent(g);

            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new Seller();
            }
        });
    }
}