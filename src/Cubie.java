import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Box;

public class Cubie {
    Box kubik;
    TransformGroup tg;

    private  Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);
    private  Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);
    private  Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);
    private  Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);
    private  Color3f Orange = new Color3f(1.0f, 0.4f, 0.0f);
    private  Color3f White = new Color3f(1.0f, 1.0f, 1.0f);
    private  Color3f Black = new Color3f(0.1f, 0.1f, 0.1f);


    private  int[] faces = { Box.TOP, Box.FRONT, Box.RIGHT,
            Box.BACK, Box.LEFT, Box.BOTTOM };

    private Appearance[] app =
            {getAppearance(0), getAppearance(1),
            getAppearance(2), getAppearance(3),
            getAppearance(4),getAppearance(5)};


    //Konstruktor ktory tworzy kostkę i koloruje sciany
        public Cubie (float size, float x, float y, float z)
        {




                    kubik = new Box(size, size, size,Box.GENERATE_NORMALS, null);

                    for (int i = 0; i < faces.length; i++) {
                        Shape3D shape = kubik.getShape(faces[i]);
                        shape.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
                        shape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
                        shape.setAppearance(app[i]);
                    }

                    //transformacja przesuwająca kostki na swoje miejsca
                    tg = new TransformGroup();
                     Transform3D transform = new Transform3D();
                     Vector3f vector = new Vector3f( x, y, z);
                     transform.setTranslation(vector);
                     tg.setTransform(transform);
                     tg.addChild(kubik);

        }
        //gettery
        public TransformGroup getTg()
        {
            return tg;
        }

        public Appearance getAppearance(int i)
        {


                Appearance apptest = new Appearance();
                Color3f kol = ktoryKolor(i);
                ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
                apptest.setColoringAttributes(att);

                return apptest;



        }

        public Box getCubie()
        {
            return kubik;
        }


        // wybor koloru bo sciany pudelka sa intami
        public Color3f ktoryKolor(int x)
        {
            switch(x)
            {
                case 0: return Red;
                case 1: return Green;
                case 2: return Blue;
                case 3: return Yellow;
                case 4: return Orange;
                case 5: return White;

            }
            return Black;
        }



}
