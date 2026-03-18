package nkasd.dls;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class AdminDashboard extends JFrame {

    // Custom colors
    private static final Color PRIMARY_COLOR = new Color(48, 63, 159);
    private static final Color SECONDARY_COLOR = new Color(74, 20, 140);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color SIDEBAR_COLOR = new Color(33, 33, 33);
    private static final Color CARD_RED = new Color(255, 82, 82);
    private static final Color CARD_GREEN = new Color(102, 187, 106);
    private static final Color CARD_BLUE = new Color(66, 165, 245);
    private static final Color CARD_PURPLE = new Color(171, 71, 188);
    private static final Color CARD_YELLOW = new Color(255, 213, 79);
    private static final Color CARD_ORANGE = new Color(255, 167, 38);
    private static final Color TEXT_LIGHT = new Color(255, 255, 255);
    private static final Color TEXT_DARK = new Color(33, 33, 33);

    // Animation variables
    private float sidebarHoverAlpha = 0f;
    private int cardHoverIndex = -1;
    private float[] cardHoverAlphas = new float[6];
    private Timer animationTimer;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        setLocationRelativeTo(null);

        // Initialize animation timer
        animationTimer = new Timer(16, e -> {
            repaint();
        });
        animationTimer.start();

        // Create custom title bar
        createTitleBar();

        // Create main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createSidebar(), createMainContent());
        splitPane.setDividerLocation(280);
        splitPane.setDividerSize(0);
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void createTitleBar() {
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(PRIMARY_COLOR);
        titleBar.setPreferredSize(new Dimension(getWidth(), 40));

        // Drag listener for moving the window
        MouseAdapter ma = new MouseAdapter() {
            private Point initialClick;

            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }

            public void mouseDragged(MouseEvent e) {
                // get location of Window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                setLocation(thisX + xMoved, thisY + yMoved);
            }
        };
        titleBar.addMouseListener(ma);
        titleBar.addMouseMotionListener(ma);

        // Close button
        JButton closeButton = new JButton("×");
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.setContentAreaFilled(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        closeButton.setForeground(TEXT_LIGHT);
        closeButton.setFocusPainted(false);
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(CARD_RED);
            }
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(TEXT_LIGHT);
            }
        });
        closeButton.addActionListener(e -> System.exit(0));

        // Minimize button
        JButton minimizeButton = new JButton("−");
        minimizeButton.setFont(new Font("Arial", Font.BOLD, 20));
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        minimizeButton.setForeground(TEXT_LIGHT);
        minimizeButton.setFocusPainted(false);
        minimizeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                minimizeButton.setForeground(CARD_YELLOW);
            }
            public void mouseExited(MouseEvent e) {
                minimizeButton.setForeground(TEXT_LIGHT);
            }
        });
        minimizeButton.addActionListener(e -> setState(JFrame.ICONIFIED));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(minimizeButton);
        buttonPanel.add(closeButton);

        titleBar.add(buttonPanel, BorderLayout.EAST);
        add(titleBar, BorderLayout.NORTH);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw hover effect
                if (sidebarHoverAlpha > 0) {
                    g2d.setColor(new Color(255, 255, 255, (int)(30 * sidebarHoverAlpha)));
                    g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 15, 15);
                }
            }
        };
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        sidebar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                new Thread(() -> {
                    for (float i = 0; i <= 1; i += 0.05f) {
                        sidebarHoverAlpha = i;
                        try { Thread.sleep(10); } catch (Exception ex) {}
                    }
                }).start();
            }
            public void mouseExited(MouseEvent e) {
                new Thread(() -> {
                    for (float i = 1; i >= 0; i -= 0.05f) {
                        sidebarHoverAlpha = i;
                        try { Thread.sleep(10); } catch (Exception ex) {}
                    }
                }).start();
            }
        });

        // Logo/Title
        JLabel logo = new JLabel("ADMIN PANEL");
        logo.setForeground(TEXT_LIGHT);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logo.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);

        // Menu items
        String[] menuItems = {
                "Dashboard",
                "Manage Users",
                "Manage Subjects",
                "Assignment Subject",
                "Assignment Student",
                "Add Parent",
                "Add Venue"
        };

        boolean[] completedStatus = {false, true, false, false, false, false, false};
        Icon[] menuIcons = {
                new ImageIcon("icons/dashboard.png"),
                new ImageIcon("icons/users.png"),
                new ImageIcon("icons/subjects.png"),
                new ImageIcon("icons/assignment.png"),
                new ImageIcon("icons/student.png"),
                new ImageIcon("icons/parent.png"),
                new ImageIcon("icons/venue.png")
        };

        for (int i = 0; i < menuItems.length; i++) {
            JPanel menuItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
            menuItemPanel.setBackground(SIDEBAR_COLOR);
            menuItemPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            menuItemPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Icon
            JLabel iconLabel = new JLabel(menuIcons[i]);

            // Label
            JLabel menuLabel = new JLabel(menuItems[i]);
            menuLabel.setForeground(TEXT_LIGHT);
            menuLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            // Checkbox (as a custom component)
            JCheckBox checkBox = new JCheckBox() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    if (isSelected()) {
                        g2d.setColor(CARD_GREEN);
                        g2d.fillRoundRect(0, 0, 18, 18, 4, 4);

                        g2d.setColor(TEXT_LIGHT);
                        g2d.setStroke(new BasicStroke(2));
                        g2d.drawLine(4, 9, 8, 13);
                        g2d.drawLine(8, 13, 14, 5);
                    } else {
                        g2d.setColor(new Color(100, 100, 100));
                        g2d.fillRoundRect(0, 0, 18, 18, 4, 4);
                    }
                }
            };
            checkBox.setSelected(completedStatus[i]);
            checkBox.setOpaque(false);
            checkBox.setPreferredSize(new Dimension(18, 18));
            checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            menuItemPanel.add(iconLabel);
            menuItemPanel.add(menuLabel);
            menuItemPanel.add(Box.createHorizontalGlue());
            menuItemPanel.add(checkBox);

            // Add hover and click effects
            menuItemPanel.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    menuItemPanel.setBackground(new Color(66, 66, 66));
                    menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                }
                public void mouseExited(MouseEvent e) {
                    menuItemPanel.setBackground(SIDEBAR_COLOR);
                    menuLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                }
                public void mouseClicked(MouseEvent e) {
                    checkBox.setSelected(!checkBox.isSelected());
                    menuItemPanel.repaint();

                    // Ripple effect
                    new Thread(() -> {
                        Point clickPoint = e.getPoint();
                        for (int r = 5; r < 100; r += 5) {
                            final int radius = r;
                            SwingUtilities.invokeLater(() -> {
                                Graphics2D g2d = (Graphics2D) menuItemPanel.getGraphics();
                                if (g2d != null) {
                                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                    g2d.setColor(new Color(255, 255, 255, 100 - radius));
                                    g2d.drawOval(clickPoint.x - radius/2, clickPoint.y - radius/2, radius, radius);
                                }
                            });
                            try { Thread.sleep(10); } catch (Exception ex) {}
                        }
                    }).start();
                }
            });

            sidebar.add(menuItemPanel);
        }

        // Add flexible space to push content to top
        sidebar.add(Box.createVerticalGlue());

        // Footer with version info
        JLabel version = new JLabel("v2.0.1");
        version.setForeground(new Color(150, 150, 150));
        version.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(version);

        return sidebar;
    }

    private JPanel createMainContent() {
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Add header with welcome message
        mainContent.add(createWelcomeHeader());

        // Add statistics cards
        mainContent.add(Box.createVerticalStrut(20));
        mainContent.add(createStatsCards());

        // Add recent activity section
        mainContent.add(Box.createVerticalStrut(30));
        mainContent.add(createRecentActivitySection());

        // Add quick actions
        mainContent.add(Box.createVerticalStrut(30));
        mainContent.add(createQuickActions());

        return mainContent;
    }

    private JPanel createWelcomeHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome back, Admin");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(TEXT_DARK);

        // Date/time
        JLabel dateLabel = new JLabel(new java.text.SimpleDateFormat("EEEE, MMMM d, yyyy").format(new java.util.Date()));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(new Color(100, 100, 100));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.add(welcomeLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(dateLabel);

        // User profile with animation
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profilePanel.setOpaque(false);

        // Circular profile picture
        JLabel profilePic = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw circular profile picture
                int diameter = Math.min(getWidth(), getHeight());
                g2d.setColor(PRIMARY_COLOR);
                g2d.fillOval(0, 0, diameter, diameter);

                // Draw initial
                g2d.setColor(TEXT_LIGHT);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));
                FontMetrics fm = g2d.getFontMetrics();
                String text = "A";
                int x = (diameter - fm.stringWidth(text)) / 2;
                int y = ((diameter - fm.getHeight()) / 2) + fm.getAscent();
                g2d.drawString(text, x, y);
            }
        };
        profilePic.setPreferredSize(new Dimension(40, 40));

        // Profile dropdown with animation
        JButton profileButton = new JButton("Admin ▼");
        profileButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        profileButton.setForeground(TEXT_DARK);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        profileButton.setFocusPainted(false);

        JPopupMenu profileMenu = new JPopupMenu();
        profileMenu.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Custom menu items
        String[] menuItems = {"Profile", "Settings", "Logout"};
        Icon[] menuIcons = {
                new ImageIcon("icons/profile.png"),
                new ImageIcon("icons/settings.png"),
                new ImageIcon("icons/logout.png")
        };

        for (int i = 0; i < menuItems.length; i++) {
            JMenuItem item = new JMenuItem(menuItems[i], menuIcons[i]);
            item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            item.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            int finalI = i;
            item.addActionListener(e -> JOptionPane.showMessageDialog(this, menuItems[finalI] + " clicked"));
            profileMenu.add(item);
        }

        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));

        profilePanel.add(profilePic);
        profilePanel.add(Box.createHorizontalStrut(10));
        profilePanel.add(profileButton);

        header.add(leftPanel, BorderLayout.WEST);
        header.add(profilePanel, BorderLayout.EAST);

        return header;
    }

    private JPanel createStatsCards() {
        JPanel cardsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        cardsPanel.setOpaque(false);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Card data: title, value, color, icon
        Object[][] cardData = {
                {"Total Students", "500", CARD_RED, "👨‍🎓"},
                {"Total Subjects", "40", CARD_GREEN, "📚"},
                {"Total Assignments", "125", CARD_BLUE, "📝"},
                {"Total Teachers", "25", CARD_PURPLE, "👩‍🏫"},
                {"Total Parents", "400", CARD_YELLOW, "👪"},
                {"Total Venues", "10", CARD_ORANGE, "🏫"}
        };

        for (int i = 0; i < cardData.length; i++) {
            final int index = i;
            JPanel card = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Draw card background with shadow
                    g2d.setColor(new Color(245, 245, 245));
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                    // Draw colored header
                    g2d.setColor((Color) cardData[index][2]);
                    g2d.fillRoundRect(0, 0, getWidth(), 5, 15, 15);

                    // Draw hover effect
                    if (cardHoverAlphas[index] > 0) {
                        g2d.setColor(new Color(255, 255, 255, (int)(100 * cardHoverAlphas[index])));
                        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                    }
                }
            };
            card.setLayout(new BorderLayout());
            card.setPreferredSize(new Dimension(200, 150));
            card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Add hover animation
            card.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    cardHoverIndex = index;
                    new Thread(() -> {
                        for (float a = 0; a <= 1; a += 0.1f) {
                            cardHoverAlphas[index] = a;
                            try { Thread.sleep(10); } catch (Exception ex) {}
                        }
                    }).start();
                }
                public void mouseExited(MouseEvent e) {
                    new Thread(() -> {
                        for (float a = 1; a >= 0; a -= 0.1f) {
                            cardHoverAlphas[index] = a;
                            try { Thread.sleep(10); } catch (Exception ex) {}
                        }
                    }).start();
                }
                public void mouseClicked(MouseEvent e) {
                    // Bounce animation
                    new Thread(() -> {
                        try {
                            card.setBounds(card.getX(), card.getY() + 5, card.getWidth(), card.getHeight() - 5);
                            Thread.sleep(50);
                            card.setBounds(card.getX(), card.getY() - 5, card.getWidth(), card.getHeight() + 5);
                        } catch (Exception ex) {}
                    }).start();
                }
            });

            // Card content
            JPanel content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.setOpaque(false);
            content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Icon
            JLabel icon = new JLabel((String) cardData[index][3]);
            icon.setFont(new Font("Segoe UI", Font.PLAIN, 30));
            icon.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Title
            JLabel title = new JLabel((String) cardData[index][0]);
            title.setFont(new Font("Segoe UI", Font.BOLD, 16));
            title.setForeground(new Color(100, 100, 100));
            title.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Value
            JLabel value = new JLabel((String) cardData[index][1]);
            value.setFont(new Font("Segoe UI", Font.BOLD, 28));
            value.setForeground(TEXT_DARK);
            value.setAlignmentX(Component.LEFT_ALIGNMENT);

            content.add(icon);
            content.add(Box.createVerticalStrut(15));
            content.add(title);
            content.add(Box.createVerticalStrut(5));
            content.add(value);

            card.add(content, BorderLayout.CENTER);
            cardsPanel.add(card);
        }

        return cardsPanel;
    }

    private JPanel createRecentActivitySection() {
        JPanel activityPanel = new JPanel();
        activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));
        activityPanel.setOpaque(false);

        // Section title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePanel.setOpaque(false);

        JLabel icon = new JLabel("🕒");
        icon.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel title = new JLabel("Recent Activity");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(TEXT_DARK);

        titlePanel.add(icon);
        titlePanel.add(title);
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        activityPanel.add(titlePanel);
        activityPanel.add(Box.createVerticalStrut(15));

        // Activity items
        String[] activities = {
                "Student A marked present",
                "New subject added",
                "Parent B registered",
                "Teacher C assigned new task"
        };

        String[] times = {
                "2 minutes ago",
                "15 minutes ago",
                "1 hour ago",
                "3 hours ago"
        };

        for (int i = 0; i < activities.length; i++) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setOpaque(false);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
            itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Animated bullet point
            JLabel bullet = new JLabel("•") {
                private float alpha = 0f;

                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                    super.paintComponent(g);
                }

                public void animate() {
                    new Thread(() -> {
                        for (float a = 0; a <= 1; a += 0.05f) {
                            alpha = a;
                            repaint();
                            try { Thread.sleep(20); } catch (Exception ex) {}
                        }
                    }).start();
                }
            };
            bullet.setFont(new Font("Segoe UI", Font.BOLD, 20));
            bullet.setForeground(PRIMARY_COLOR);
            bullet.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
            bullet.validate();

            // Activity content
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setOpaque(false);

            JLabel activityLabel = new JLabel(activities[i]);
            activityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            activityLabel.setForeground(TEXT_DARK);

            JLabel timeLabel = new JLabel(times[i]);
            timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            timeLabel.setForeground(new Color(150, 150, 150));

            contentPanel.add(activityLabel);
            contentPanel.add(Box.createVerticalStrut(2));
            contentPanel.add(timeLabel);

            itemPanel.add(bullet, BorderLayout.WEST);
            itemPanel.add(contentPanel, BorderLayout.CENTER);

            // Add hover effect
            itemPanel.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    activityLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    activityLabel.setForeground(PRIMARY_COLOR);
                }
                public void mouseExited(MouseEvent e) {
                    activityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    activityLabel.setForeground(TEXT_DARK);
                }
            });

            activityPanel.add(itemPanel);

            // Add separator if not last item
            if (i < activities.length - 1) {
                JSeparator separator = new JSeparator();
                separator.setForeground(new Color(230, 230, 230));
                separator.setAlignmentX(Component.LEFT_ALIGNMENT);
                activityPanel.add(separator);
            }
        }

        return activityPanel;
    }

    private JPanel createQuickActions() {
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setOpaque(false);

        // Section title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePanel.setOpaque(false);

        JLabel icon = new JLabel("⚡");
        icon.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel title = new JLabel("Quick Actions");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(TEXT_DARK);

        titlePanel.add(icon);
        titlePanel.add(title);
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        actionsPanel.add(titlePanel);
        actionsPanel.add(Box.createVerticalStrut(15));

        // Action buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Object[][] buttonData = {
                {"Manage Users", CARD_RED, "icons/users.png"},
                {"Manage Subjects", CARD_GREEN, "icons/subjects.png"},
                {"Assignment Subject", CARD_BLUE, "icons/assignment.png"},
                {"Add Parent", CARD_PURPLE, "icons/parent.png"},
                {"Add Venue", CARD_ORANGE, "icons/venue.png"}
        };

        for (Object[] data : buttonData) {
            JButton button = new JButton((String) data[0], new ImageIcon((String) data[2])) {
                private float hoverAlpha = 0f;

                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Draw button background
                    g2d.setColor((Color) data[1]);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                    // Draw hover effect
                    if (hoverAlpha > 0) {
                        g2d.setColor(new Color(255, 255, 255, (int)(50 * hoverAlpha)));
                        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    }

                    // Draw icon and text
                    super.paintComponent(g);
                }
            };

            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setForeground(TEXT_LIGHT);
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setIconTextGap(15);
            button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Add hover animation
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    new Thread(() -> {
                        for (float a = 0; a <= 1; a += 0.1f) {
//                            ((JButton)e.getSource()).AccessibleAWTComponent = a;
                            ((JButton)e.getSource()).repaint();
                            try { Thread.sleep(10); } catch (Exception ex) {}
                        }
                    }).start();
                }
                public void mouseExited(MouseEvent e) {
                    new Thread(() -> {
                        for (float a = 1; a >= 0; a -= 0.1f) {
//                            ((JButton)e.getSource()).hoverAlpha = a;
                            ((JButton)e.getSource()).repaint();
                            try { Thread.sleep(10); } catch (Exception ex) {}
                        }
                    }).start();
                }
            });

            button.addActionListener(e -> {
                // Pulse animation when clicked
                new Thread(() -> {
                    try {
                        button.setBorder(BorderFactory.createEmptyBorder(14, 22, 14, 22));
                        Thread.sleep(50);
                        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
                    } catch (Exception ex) {}
                }).start();

                JOptionPane.showMessageDialog(this, ((JButton)e.getSource()).getText() + " action performed");
            });

            buttonsPanel.add(button);
        }

        actionsPanel.add(buttonsPanel);
        return actionsPanel;
    }

    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Set custom UI defaults
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("ProgressBar.arc", 10);
            UIManager.put("TextComponent.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            AdminDashboard dashboard = new AdminDashboard();

            // Add fade-in animation for the whole window
            dashboard.setOpacity(0f);
            new Thread(() -> {
                try {
                    for (float i = 0; i <= 1; i += 0.05f) {
                        final float opacity = i;
                        SwingUtilities.invokeLater(() -> dashboard.setOpacity(opacity));
                        Thread.sleep(20);
                    }
                } catch (Exception e) {}
            }).start();
        });
    }
}