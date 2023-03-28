
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


class GraphicalApplication extends JPanel {
    // The text intended to be displayed in the parent window's title bar
    private String title;
    // The size of the panel
    protected final Dimension PANEL_DIMENSION;

    @Override
    public Dimension getPreferredSize() {
        return PANEL_DIMENSION;
    }

    public GraphicalApplication(String title, int width, int height) {
        PANEL_DIMENSION = new Dimension(width, height);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void run() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // This window will exist while the graphical program executes
                JFrame window = new JFrame(title);
                window.add(GraphicalApplication.this);
                window.pack();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
            }
        });
    }
}


/**
 * An interactive visual application that allows the user to explore geometric
 * lines
 */
@SuppressWarnings("serial")
public class InterpolatingPolynomialVisualization extends GraphicalApplication {
        protected static final int NORMAL_OFFSET = 4;
        protected static final int ACTIVE_OFFSET = 8;
        protected static final int NORMAL_SIZE = 8;
        protected static final int ACTIVE_SIZE = 16;
        protected static final int CLOSENESS = 8;
        protected static final Color LIGHT_GRAY = new Color(220, 220, 220);
        protected static final Color DARK_GREEN = new Color(0, 150, 0);
        protected static final Color DARK_BLUE = new Color(0, 0, 200);
        protected ArrayList<Point> points = new ArrayList<>();
        protected Point activePoint = null;
        protected boolean isDragging = false;

        protected Dimension dimension;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (activePoint != null) {
                    isDragging = true;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX() - getWidth() / 2,
                    y = getHeight() / 2 - e.getY();
                isDragging = false;
                if (activePoint == null) {
                    points.add(new Point(x, y));
                    points.sort((Point p1, Point p2) -> {
                        return (p1.x < p2.x) ? -1 : (p1.x > p2.x) ? 1 : 0;
                    });
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                activePoint = null;
                if (!isDragging) {
                    checkActive(e.getX() - getWidth() / 2, getHeight() / 2 - e.getY());
                    points.sort((Point p1, Point p2) -> {
                        return (p1.x < p2.x) ? -1 : (p1.x > p2.x) ? 1 : 0;
                    });
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && activePoint != null) {
                    activePoint.x = e.getX() - getWidth() / 2;
                    activePoint.y = getHeight() / 2 - e.getY();
                }
                repaint();
            }
        };

        public InterpolatingPolynomialVisualization(int width, int height) {
            super("InterpolatingPolynomialVisualization", width, height);
            setBackground(Color.WHITE);
            setLayout(null);
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
            setFocusable(true);
            //dimension = new Dimension(width, height);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyTyped(e);
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            System.out.println("You pressed the UP key");
                            break;
                        case KeyEvent.VK_DOWN:
                            System.out.println("You pressed the DOWN key");
                            break;
                        case KeyEvent.VK_DELETE:
                            if (activePoint != null) {
                                points.remove(activePoint);
                                activePoint = null;
                            }
                            break;
                        case KeyEvent.VK_ESCAPE:
                            points.clear();
                            break;
                    }
                    repaint();
                }
            });
        }

        protected void checkActive(int x, int y) {
            for (Point p : points) {
                if (near(p, x, y)) {
                    activePoint = p;
                }
            }
        }

        private boolean near(Point p, double x, double y) {
            double dx = p.x - x, dy = p.y - y;
            dx = (dx > 0) ? dx : -dx;
            dy = (dy > 0) ? dy : -dy;
            return dx < CLOSENESS && dy < CLOSENESS;
        }

        protected void drawPoint(Graphics2D g, Point p) {
            int x = (int) Math.round(p.x), y = (int) Math.round(p.y);
            g.fillOval(x - NORMAL_OFFSET, y - NORMAL_OFFSET, NORMAL_SIZE, NORMAL_SIZE);
            if (p == activePoint) {
                g.drawOval(x - ACTIVE_OFFSET, y - ACTIVE_OFFSET, ACTIVE_SIZE, ACTIVE_SIZE);
            }
//            g.scale(1, -1);
//            g.drawString(p.toString(), x + 10, -y - 10);
//            g.scale(1, -1);

        }


        private void drawGrid(Graphics2D g, int interval) {
            int w = getWidth(), h = getHeight(), maxX = w / 2, maxY = h / 2;
            g.setColor(LIGHT_GRAY);
            // Draw the minor grid lines
            for (int i = 0; i < w / 2; i += interval) {
                g.drawLine(-maxX, i, maxX, i);
                g.drawLine(-maxX, -i, maxX, -i);
                g.drawLine(i, -maxY, i, maxY);
                g.drawLine(-i, -maxY, -i, maxY);

            }
            // Draw the axes
            g.setColor(Color.BLACK);
            var strokeSaved = g.getStroke();
            g.setStroke(new BasicStroke(2));
            g.drawLine(-maxX, 0, maxX, 0);
            g.drawLine(0, -maxY, 0, maxY);

            // Draw the x-axis arrow heads
            g.drawLine(-maxX, 0, -maxX + 10, -7);
            g.drawLine(-maxX, 0, -maxX + 10, 7);
            g.drawLine(maxX, 0, maxX - 10, -7);
            g.drawLine(maxX, 0, maxX - 10, 7);

            // Draw the y-axis arrow heads
            g.drawLine(0, -maxY, -7, -maxY + 10);
            g.drawLine(0, -maxY, 7, -maxY + 10);
            g.drawLine(0, maxY, -7, maxY - 10);
            g.drawLine(0, maxY, 7, maxY - 10);

            g.setStroke(strokeSaved);
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform at = g2.getTransform();
            g2.translate(getWidth() / 2, getHeight() / 2);
            g2.scale(1, -1); // Invert y-axis
            drawGrid(g2, 20);

            // Draw the points
            for (Point p : points) {
                drawPoint(g2, p);
            }
            
            int n = points.size();
            System.out.println("Points size = " + n);
            if (n > 0) {
                g2.setColor(Color.BLUE);
                // Draw the polynomial curve interpolating these points
                double[] x = new double[points.size()];
                double[] y = new double[points.size()];
                for (int i = 0; i < x.length; i++) {
                    x[i] = points.get(i).x;
                    y[i] = points.get(i).y;
                }
                InterpolatedPolynomial poly = new PolynomialCalculator();
                //*******************************************************
                // The next statement uses the coef method.
                double[] coefficients = poly.coef(x, y);
                for (double xVal = x[0]; xVal <= x[x.length - 1]; xVal += 0.001) {
                    //***************************************************
                    // The next statement uses the eval method.
                    int yVal = (int) poly.eval(x, coefficients, xVal);
                    g2.drawRect((int) xVal, yVal, 1, 1);
                }
                g2.scale(1, -1); // Re-invert the y-axis to print the text right-side up
                g2.drawString(poly.toString(x, coefficients), -370, -370);
                g2.setTransform(at);
                g2.scale(1,  -1);
            }
            
            g2.scale(1, -1); // Re-invert the y-axis to print the text right-side up

            g2.setTransform(at);
        }

    /**
     * Starting point of the application's execution
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        new InterpolatingPolynomialVisualization(800, 800).run();
    }
}
