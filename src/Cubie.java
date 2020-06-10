import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.geometry.Box;
public class Cubie {
    Box kubik;
    TransformGroup przesuniecie;
    TransformGroup rotacjaX;
    Shape3D sciana;
    Transform3D tfX = new Transform3D();

    private  Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);
    private  Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);
    private  Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);
    private  Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);
    private  Color3f Orange = new Color3f(1.0f, 0.7f, 0.1f);
    private  Color3f White = new Color3f(1.0f, 1.0f, 1.0f);
    private  Color3f Black = new Color3f(0.1f, 0.1f, 0.1f);
    
    private  int[] faces = { Box.FRONT, Box.BACK, Box.RIGHT,
            Box.LEFT, Box.TOP, Box.BOTTOM };

    private Appearance[] app =
            {getAppearance(0), getAppearance(1),
                    getAppearance(2), getAppearance(3),
                    getAppearance(4),getAppearance(5)};


    //Konstruktor ktory tworzy kostkę i koloruje sciany
    public Cubie (float size, float x, float y, float z)
    {

        kubik = new Box(size, size, size,Box.GENERATE_NORMALS, null);

        for (int i = 0; i < faces.length; i++) {
            sciana = kubik.getShape(faces[i]);
            sciana.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
            sciana.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
            sciana.setAppearance(app[i]);


        }

        //transformacja przesuwająca kostki na swoje miejsca

        Transform3D transform = new Transform3D();
        Vector3d vector = new Vector3d( x, y, z);
        transform.setTranslation(vector);
        Transform3D temp = new Transform3D();

        przesuniecie = new TransformGroup(transform);
        przesuniecie.addChild(kubik);

        temp.mul(tfX);

        rotacjaX = new TransformGroup(temp);
        rotacjaX.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacjaX.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacjaX.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        rotacjaX.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        rotacjaX.addChild(przesuniecie);

    }

    // wybor koloru bo sciany pudelka sa intami
    public Color3f ktoryKolor(int x)
    {
        switch(x)
        {
            case 0: return Green;
            case 1: return Yellow;
            case 2: return Blue;
            case 3: return Orange;
            case 4: return Red;
            case 5: return White;
        }
        return Black;
    }

    //@@@@@@@@@@@@@@@ GETTERY @@@@@@@@@@@


    public TransformGroup getTgX()
    {
        return rotacjaX;
    }

    public Transform3D getTransformX()
    {


        return tfX;
    }

    public Appearance getAppearance(int i)
    {


        Appearance apptest = new Appearance();
        Color3f kol = ktoryKolor(i);
        ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
        apptest.setColoringAttributes(att);

        return apptest;
    }

    public Color3f getColor(int i)
    {
        Color3f temp = new Color3f();
        Shape3D  szejp =new Shape3D();
        szejp =  kubik.getShape(faces[i]);
        szejp.getAppearance().getColoringAttributes().getColor(temp);
        return temp;

    }

    public int getColorInt(int numer_sciany)
    {
        int numer_koloru;
        Color3f temp = new Color3f();
        temp = getColor(numer_sciany);
        System.out.println(temp);

        if(temp.equals(Green)){numer_koloru=0;System.out.println("Zielony");}
        else if(temp.equals(Yellow)){numer_koloru=1;System.out.println("Zólty");}
        else if(temp.equals(Blue)){numer_koloru=2;System.out.println("Niebieski");}
        else if(temp.equals(Orange)){numer_koloru=3;System.out.println("Pomaranczowy");}
        else if(temp.equals(Red)){numer_koloru=4;System.out.println("czerwony ");}
        else if(temp.equals(White)){numer_koloru=5;System.out.println("Bialy");}
        else {System.out.println("Bledny kolor");numer_koloru=6;}

        return numer_koloru;
    }

    public Box getCubie()
    {
        return kubik;
    }

    //@@@@@@@@@@@@@@@@ SETTERY @@@@@@@@@@@@@@@@@@@
    public void setColor(int i,Color3f kolor)
    {
        Appearance tempapp = new Appearance();
        Color3f kol = kolor;
        ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
        tempapp.setColoringAttributes(att);



        Shape3D  szejp;
        szejp =  kubik.getShape(i);
        szejp.setAppearance(tempapp);


    }

    public void setColor(int numer_sciany, int numer_koloru)
    {
        Appearance tempapp = new Appearance();
        Color3f kol = ktoryKolor(numer_koloru);
        ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
        tempapp.setColoringAttributes(att);

        Shape3D  szejp;
        szejp =  kubik.getShape(faces[numer_sciany]);
        szejp.setAppearance(tempapp);

    }

}