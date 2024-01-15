import javax.swing.*;
import java.awt.*;

public class TaxCalculatorGUI {
    private JFrame frame;
    private JComboBox<String> taxYearBox;
    private JTextField taxableIncomeField;
    private JTextField ageField;
    private JLabel resultLabel;
    private JButton calcButton;

    public TaxCalculatorGUI() {
        frame = new JFrame("SARS TAX Calculator");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();
        setFrameVisible();
    }

    private void initComponents() {
        String[] units = {"2022", "2023", "2024"};
        taxYearBox = new JComboBox<>(units);
        taxableIncomeField = new JTextField();
        ageField = new JTextField();
        resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        calcButton = new JButton("Calculate Tax");
        calcButton.addActionListener(e -> performCalculations());

        Font largerFont = new Font("Arial", Font.PLAIN, );
        taxYearBox.setFont(largerFont);
        taxYearBox.setPreferredSize(new Dimension(100, taxYearBox.getPreferredSize().height));

        taxableIncomeField.setFont(largerFont);
        taxableIncomeField.setPreferredSize(new Dimension(100, taxableIncomeField.getPreferredSize().height));

        ageField.setFont(largerFont);
        ageField.setPreferredSize(new Dimension(50, ageField.getPreferredSize().height));

        calcButton.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        mainPanel.setBackground(new Color(255, 255, 255));

        JLabel unitLabel = new JLabel("Tax calculator");
        Font labelFont = new Font("Times New Roman", Font.BOLD | Font.BOLD, 35);
        unitLabel.setFont(labelFont);
        unitLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(unitLabel, BorderLayout.NORTH);

        JPanel roundedPanel = createRoundedPanel();
        mainPanel.add(roundedPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
    }

    private JPanel createRoundedPanel() {
        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int arc = 20;
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            }
        };
        roundedPanel.setLayout(new GridBagLayout());
        roundedPanel.setBackground(new Color(128, 128, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        addLabelAndComponent("Tax year:", taxYearBox, gbc, roundedPanel, GridBagConstraints.LINE_END, GridBagConstraints.LINE_START);
        addLabelAndComponent("Taxable Income:", taxableIncomeField, gbc, roundedPanel, GridBagConstraints.LINE_END, GridBagConstraints.LINE_START);
        addLabelAndComponent("Age:", ageField, gbc, roundedPanel, GridBagConstraints.LINE_END, GridBagConstraints.LINE_START);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        roundedPanel.add(calcButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        roundedPanel.add(resultLabel, gbc);

        return roundedPanel;
    }

    private void addLabelAndComponent(String labelText, JComponent component, GridBagConstraints gbc, JPanel panel, int labelAnchor, int componentAnchor) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = labelAnchor;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = componentAnchor;
        panel.add(component, gbc);
    }

    private void setFrameVisible() {
        frame.setVisible(true);
    }

    private void performCalculations() {
        try {
            String taxYear = (String) taxYearBox.getSelectedItem();
            double taxableIncome = Double.parseDouble(taxableIncomeField.getText());
            int age = Integer.parseInt(ageField.getText());
            if (age == 0){
                JOptionPane.showMessageDialog(frame, "Input correct age", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

            double result = calculateIncomeTax(taxYear, taxableIncome, age);

            if (result == 0){
                JOptionPane.showMessageDialog(frame, "No tax payable R"+ result, "Output", JOptionPane.ERROR_MESSAGE);
            }
            String tax = String.format("%.2f", result);
            resultLabel.setText("Tax " + taxYear + ": R" + tax);
            

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error during conversion", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static double calculateIncomeTax(String taxYear, double taxableIncome, int age) {
        double threshold = getTaxThreshold(age, taxYear);
        double rebate = getRebate(age, taxYear);
        double total_tax = 0;

        if (taxableIncome <= threshold) {
            return 0;
        }

        if (taxYear.equals("2022")) {
            if (taxableIncome > 0 && taxableIncome <= 216_200) {
                total_tax = taxableIncome * 0.18;
            } else if (taxableIncome <= 337_800) {
                total_tax = 38_916 + 0.26 * (taxableIncome - 216_200);
            } else if (taxableIncome <= 467_500) {
                total_tax = 70_532 + 0.31 * (taxableIncome - 337_800);
            } else if (taxableIncome <= 613_600) {
                total_tax = 110_739 + 0.36 * (taxableIncome - 467_500);
            } else if (taxableIncome <= 782_200) {
                total_tax = 163_335 + 0.39 * (taxableIncome - 613_600);
            } else if (taxableIncome <= 1_656_600) {
                total_tax = 229_089 + 0.41 * (taxableIncome - 782_200);
            } else {
                total_tax = 587_593 + 0.45 * (taxableIncome - 1_656_600);
            }
        } else if (taxYear.equals("2023")) {
            if (taxableIncome > 0 && taxableIncome <= 226_000) {
                total_tax = taxableIncome * 0.18;
            } else if (taxableIncome <= 353_100) {
                total_tax = 40_680 + 0.26 * (taxableIncome - 226_000);
            } else if (taxableIncome <= 488_700) {
                total_tax = 73_726 + 0.31 * (taxableIncome - 353_100);
            } else if (taxableIncome <= 641_400) {
                total_tax = 115_762 + 0.36 * (taxableIncome - 488_700);
            } else if (taxableIncome <= 817_600) {
                total_tax = 170_734 + 0.39 * (taxableIncome - 641_400);
            } else if (taxableIncome <= 1_731_600) {
                total_tax = 239_452 + 0.41 * (taxableIncome - 817_600);
            } else {
                total_tax = 614_192 + 0.45 * (taxableIncome - 1_731_600);
            }
        } else if (taxYear.equals("2024")) {
            if (taxableIncome > 0 && taxableIncome <= 237_100) {
                total_tax = taxableIncome * 0.18;
            } else if (taxableIncome <= 370_500) {
                total_tax = 42_678 + 0.26 * (taxableIncome - 237_100);
            } else if (taxableIncome <= 512_800) {
                total_tax = 77_362 + 0.31 * (taxableIncome - 370_500);
            } else if (taxableIncome <= 673_000) {
                total_tax = 121_475 + 0.36 * (taxableIncome - 512_800);
            } else if (taxableIncome <= 857_900) {
                total_tax = 179_147 + 0.39 * (taxableIncome - 673_000);
            } else if (taxableIncome <= 1_817_000) {
                total_tax = 251_258 + 0.41 * (taxableIncome - 857_900);
            } else {
                total_tax = 644_489 + 0.45 * (taxableIncome - 1_817_000);
            }
        }

        return total_tax - rebate;
    }



    private static double getRebate(int age, String taxYear) {
        if (taxYear == "2022") {
            if (age < 65) {
                return 15_714;
            } else if (age >= 65 && age < 75) {
                return 15_714 + 8_613;
            } else if (age >= 75) {
              return 15_714 + 8_613 + 2_871;
            }else{
                return 0;
            }
            
        } else if (taxYear == "2023") {
            if (age < 65) {
                return 16_425;
            } else if (age >= 65 && age < 75) {
                return 16_425 + 9_000;
            } else if (age >= 75) {
              return 16_425 + 9_000 + 2_997;
            }else{
                return 0;
            }
        } else if (taxYear == "2024") {
            if (age < 65) {
                return 17_235;
            } else if (age >= 65 && age < 75) {
                return 17_235 + 9_444;
            } else if (age >= 75) {
              return 17_235 + 9_444 + 3_145;
            }else{
                return 0;
            }
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }
    }
    

    private static double getTaxThreshold(int age, String taxYear) {
        // Provide the tax threshold based on age and tax year
        if (taxYear == "2022") {
            if (age > 0 && age < 65) {
                return 87_300;
            } else if (age >= 65 && age <= 75) {
                return 135_150;
            } else {
                return 151_100;
            }
        } else if (taxYear == "2023") {
           if (age > 0 && age < 65) {
                return 91_250;
            } else if (age >= 65 && age <= 75) {
                return 141_250;
            } else {
                return 157_900;
            }
        } else if (taxYear == "2024") {
           if (age > 0 && age < 65) {
                return 95_750;
            } else if (age >= 65 && age <= 75) {
                return 148_217;
            } else {
                return 165_689;
            }
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaxCalculatorGUI::new);
    }
}
