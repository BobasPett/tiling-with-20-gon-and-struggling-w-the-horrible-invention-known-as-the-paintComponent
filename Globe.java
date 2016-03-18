import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Globe extends JPanel {

    double size = 10;
    double angle = 0;
    double x =0;
    double y = 0;



    double radius = size / (2 * Math.sin(9 * (Math.PI / 180)));
    double w = Math.cos(9 * (Math.PI / 180)) * radius;
    double d = w + radius;
    double radian = Math.PI / 180;

    double moveup = 4 * (size * Math.sqrt((2 * Math.cos(Math.PI / 10) - (1 / 4))) + size * (Math.sin(Math.PI / 5) + 1)) + size * (Math.cos(Math.PI / 5) + 10 * Math.sin(Math.PI / 20) + 1);
    double moveup0 = size * (5 + 4 * Math.sin(Math.PI / 10) + 2 * Math.cos(Math.PI / 10)) + d;
    double moveup1 = 2 * size * (2 * Math.sin(2 * Math.PI / 5) + 1) + 2 * size * Math.sin(Math.PI / 10) + 4 * size + d;


    public static void circle(double x, double y, double r, String color, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2.0f));
        if (color == "green") g2.setPaint(Color.GREEN);
        if (color == "blue") g2.setPaint(Color.BLUE);
        if (color == "red") g2.setPaint(Color.RED);


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.draw(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));

    }
    public static void printcords(double[][] cords) {
        System.out.print("{");
        for (int i = 0; i < cords.length; i++) {
            System.out.print("(" + cords[i][0] + "," + cords[i][1] + ")");
            if (i < (cords.length - 1)) System.out.print(",");
        }
        System.out.println("}");
    }
    public static void printcords(int[] cords) {
        System.out.print("{");
        for (int i = 0; i < cords.length; i++) {
            System.out.print(cords[i]);
            if (i < (cords.length - 1)) System.out.print(",");
        }
        System.out.println("}");
    }

    public void drawpath(ArrayList<ArrayList<Double>> cords,Graphics2D g2) {
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, cords.size());
        path.moveTo(cords.get(0).get(0), cords.get(0).get(1));

        for (int i = 1; i < cords.size(); i++) {
            path.lineTo(cords.get(i).get(0), cords.get(i).get(1));
        }
        path.lineTo(cords.get(0).get(0), cords.get(0).get(1));
        g2.draw(path);
    }
    public ArrayList<Double> draw(double size) {
        ArrayList<Double> result = new ArrayList<Double>();

        x = x + (Math.cos(angle) * size);
        y = y + (Math.sin(angle) * size);

        result.add(y);
        result.add(x);

        return result;
    }




    public ArrayList<ArrayList<Double>> f1(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        ArrayList<ArrayList<Double>> points = new ArrayList<ArrayList<Double>>();


        for (int i = 0; i < 20; i++) {


            if (i % 2 == 0) {

                if (i != 0 && i != 10) {  //draw except for top and bottom


                    angle -= Math.PI / 2;//right

                    x = x + (Math.cos(angle) * 2 * size * (1 + Math.sin(Math.PI / 10)));
                    y = y + (Math.sin(angle) * 2 * size * (1 + Math.sin(Math.PI / 10)));//moveforward

                    angle += Math.PI / 2;//left

                    x = x + (Math.cos(angle) * size);
                    y = y + (Math.sin(angle) * size);//moveforward

                    angle -= Math.PI;//right

                    drawpath(jewl(g), g2);

                    x = x + (Math.cos(angle) * size);
                    y = y + (Math.sin(angle) * size);//moveforward

                    angle -= Math.PI / 2;//right

                    x = x + (Math.cos(angle) * 2 * size * (1 + Math.sin(Math.PI / 10)));
                    y = y + (Math.sin(angle) * 2 * size * (1 + Math.sin(Math.PI / 10)));//moveforward

                    angle -= Math.PI / 2;//right



                }


                int[] xpoints = new int[4];
                int[] ypoints = new int[4];
                for (int j = 0; j < 4; j++) {
                    points.add(draw(size));

                    xpoints[j] = (int) (Math.round(points.get(points.size()-1).get(0)));
                    ypoints[j] = (int) (Math.round(points.get(points.size()-1).get(1)));

                    angle -= Math.PI / 2;
                }

                Polygon p = new Polygon(xpoints, ypoints, 4);
                ((Graphics2D) g).setPaint(Color.green);
                g.fillPolygon(p);


            }
            else {

                int[] xpoints = new int[5];
                int[] ypoints = new int[5];
                for (int j = 0; j < 5; j++) {
                    points.add(draw(size));

                    xpoints[j] = (int) (Math.round(points.get(points.size()-1).get(0)));
                    ypoints[j] = (int) (Math.round(points.get(points.size()-1).get(1)));


                    angle -= 2 * Math.PI / 5;
                }
                Polygon p = new Polygon(xpoints, ypoints, 5);
                ((Graphics2D) g).setPaint(Color.RED);
                g.fillPolygon(p);

            }
            points.add(draw(size));
            angle += Math.PI / 10;//18 deg

        }

        drawpath(points,g2);
        return points;
    }




    public ArrayList<ArrayList<Double>> jewl(Graphics g) {

        ArrayList<ArrayList<Double>> points = new ArrayList<ArrayList<Double>>();


        int[] xpoints = new int[5];
        int[] ypoints = new int[5];


        for (int i = 0; i < 5; i++) {
            int[] xpoints1 = new int[4];
            int[] ypoints1 = new int[4];
            for (int j = 0; j < 4; j++) {

                if (j == 1) {
                    int[] xpoints2 = new int[4];
                    int[] ypoints2 = new int[4];
                    for (int k = 0; k < 4; k++) {
                        points.add(draw(size));
                        xpoints2[k] = (int) (Math.round(points.get(points.size()-1).get(0)));
                        ypoints2[k] = (int) (Math.round(points.get(points.size()-1).get(1)));
                        if (k % 2 == 0) angle += Math.PI * 2 / 5;
                        else angle += Math.PI * 3 / 5;
                    }
                    Polygon p = new Polygon(xpoints2, ypoints2, 4);
                    ((Graphics2D) g).setPaint(Color.MAGENTA);
                    g.fillPolygon(p);
                }

                else if (j == 2) {
                    int[] xpoints2 = new int[6];
                    int[] ypoints2 = new int[6];
                    for (int k = 0; k < 2; k++) {
                        points.add(draw(size));
                        xpoints2[3*k] = (int) (Math.round(points.get(points.size()-1).get(0)));
                        ypoints2[3*k] = (int) (Math.round(points.get(points.size()-1).get(1)));
                        angle += Math.PI / 10;
                        points.add(draw(size));
                        xpoints2[3*k+1] = (int) (Math.round(points.get(points.size()-1).get(0)));
                        ypoints2[3*k+1] = (int) (Math.round(points.get(points.size()-1).get(1)));
                        angle += (4 * Math.PI) / 5;
                        points.add(draw(size));
                        xpoints2[3*k+2] = (int) (Math.round(points.get(points.size()-1).get(0)));
                        ypoints2[3*k+2] = (int) (Math.round(points.get(points.size()-1).get(1)));
                        angle += Math.PI / 10;
                    }

                    printcords(ypoints2);
                    Polygon p = new Polygon(xpoints2, ypoints2, 6);
                    ((Graphics2D) g).setPaint(Color.cyan);
                    g.fillPolygon(p);

                }

                points.add(draw(size));
                xpoints1[j] = (int) (Math.round(points.get(points.size()-1).get(0)));
                ypoints1[j] = (int) (Math.round(points.get(points.size()-1).get(1)));
                angle -= Math.PI / 2;

            }

            Polygon p = new Polygon(xpoints1, ypoints1, 4);
            ((Graphics2D) g).setPaint(Color.BLUE);
            g.fillPolygon(p);

            points.add(draw(size));
            xpoints[i] = (int) (Math.round(points.get(points.size()-1).get(0)));
            ypoints[i] = (int) (Math.round(points.get(points.size()-1).get(1)));
            angle += 2 * Math.PI / 5;
        }
        Polygon p = new Polygon(xpoints, ypoints, 5);
        ((Graphics2D) g).setPaint(Color.white);
        g.fillPolygon(p);


        return points;

    }


    public void  tile(int row,int col,Graphics g) {


        for (int i = 0; i < col; i++) {

            for (int j = 0; j < row; j++) {

                f1(g);


                if (j != row - 1) {//if the tile is not the last one
                    if (i % 2 == 0) {
                        angle -= Math.PI / 2;//right
                        x = x + (Math.cos(angle) * moveup0);
                        y = y + (Math.sin(angle) * moveup0);//moveforward
                        angle += Math.PI / 2;//left

                    }
                    else {
                        angle += Math.PI / 2;//left
                        x = x + (Math.cos(angle) * moveup0);
                        y = y + (Math.sin(angle) * moveup0);//moveforward
                        angle -= Math.PI / 2;//right
                    }
                }
            }

            if(i%2==0){
                angle+=(9/20)*Math.PI;//81
                x = x + (Math.cos(angle) *radius);
                y = y + (Math.sin(angle) *radius);//moveforward

                angle-=Math.PI/4;//45
                x = x + (Math.cos(angle) *2*(Math.cos(Math.PI/20)*radius+(size/(2*Math.tan(Math.PI/10))))+size*(2*Math.sin(66*radian)+1));
                y = y + (Math.sin(angle) *2*(Math.cos(Math.PI/20)*radius+(size/(2*Math.tan(Math.PI/10))))+size*(2*Math.sin(66*radian)+1));//moveforward

                angle-=(7*Math.PI)/10;//126
                x = x + (Math.cos(angle) *Math.cos(Math.PI/20)*radius);
                y = y + (Math.sin(angle) *Math.cos(Math.PI/20)*radius);//moveforward


                angle-=Math.PI/2;//90
                x = x + (Math.cos(angle) *(size/2));
                y = y + (Math.sin(angle) *(size/2));//moveforward
                angle-=Math.PI;//180
            }



            else {
                angle+=(9/20)*Math.PI;
                x = x + (Math.cos(angle) *radius);
                y = y + (Math.sin(angle) *radius);//moveforward

                angle-=(117/180)*Math.PI;
                x = x + (Math.cos(angle) *2*(Math.cos(Math.PI/20)*radius+(size/(2*Math.tan(Math.PI/10))))+size*(2*Math.sin(66*radian)+1));
                y = y + (Math.sin(angle) *2*(Math.cos(Math.PI/20)*radius+(size/(2*Math.tan(Math.PI/10))))+size*(2*Math.sin(66*radian)+1));//moveforward

                angle-=(3*Math.PI)/10;
                x = x + (Math.cos(angle) *Math.cos(Math.PI/20)*radius);
                y = y + (Math.sin(angle) *Math.cos(Math.PI/20)*radius);//moveforward


                angle-=Math.PI/2;
                x = x + (Math.cos(angle) *(size/2));
                y = y + (Math.sin(angle) *(size/2));//moveforward
                angle-=Math.PI;
            }


        }








    }




    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1.0f));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


        /*methods:  void tile(int row,int col,Graphics g), void drawpath(ArrayList<ArrayList<Double>> cords,Graphics2D g2),
         public ArrayList<ArrayList<Double>> f1(Graphics g)
        */



        g2.setPaint(Color.GREEN);
        x = (3*getWidth() / 4);
        y = (3*getHeight() / 4);
        angle = 0;

        tile(4,4,g);

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Jframe");

        Globe a = new Globe();
        frame.add(a);
        frame.setSize(1200,1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);

    }
}




