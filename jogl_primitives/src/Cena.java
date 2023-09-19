import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
/**
 *
 * @author Kakugawa
 */
public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;    
    GLU glu;
    
    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -10;
        xMax = yMax = zMax = 10;
    }

    @Override
    public void display(GLAutoDrawable drawable) {  
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 0, 0, 1);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);       
        gl.glLoadIdentity(); //lê a matriz identidade

        //CENA

        //Linhas

        //Linha reta vertical
        gl.glColor3f(1,1,1);
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0,0);
        gl.glVertex2f( 0,10);
        gl.glEnd();

        //Linha reta horizontal
        gl.glColor3f(1,1,1);
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f( 0,0);
        gl.glVertex2f( 10,0);
        gl.glEnd();

        //Linha diagonal inferior esquerda
        gl.glColor3f(1,1,1);
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(-10, -10);
        gl.glEnd();

        //Pontos
        gl.glColor3f(1,1,1);
        gl.glPointSize(5);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(-9, 9);
        gl.glVertex2f(-7, 9);
        gl.glVertex2f(-9, 7);
        gl.glVertex2f(-7, 7);
        gl.glEnd();

        //Linhas em Loop
        gl.glColor3f(1,1,1);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(-2, 5);
        gl.glVertex2f(-2,2);
        gl.glVertex2f(-3, 3);
        gl.glVertex2f(-4, 2);
        gl.glVertex2f(-5, 3);
        gl.glVertex2f(-4,5);
        gl.glVertex2f(-3, 4);
        gl.glEnd();

        //Quadrados
        gl.glColor3f(1,1,1);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(6, 4);
        gl.glVertex2f(7, 4);
        gl.glVertex2f(7, 3);
        gl.glVertex2f(6, 3);

        gl.glVertex2f(7, 3);
        gl.glVertex2f(8, 3);
        gl.glVertex2f(8, 2);
        gl.glVertex2f(7, 2);

        gl.glVertex2f(8, 2);
        gl.glVertex2f(9, 2);
        gl.glVertex2f(9, 1);
        gl.glVertex2f(8, 1);
        gl.glEnd();

        //Poligono
        gl.glColor3f(1, 1, 1);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-5, -2);
        gl.glVertex2f(-7, -1);
        gl.glVertex2f(-9, -4);
        gl.glVertex2f(-3, -5);
        gl.glVertex2f(-2, -1);
        gl.glEnd();

        //Triangle fan
        gl.glColor3f(1, 1,1);
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(6, -6);
        gl.glVertex2f(8, -7);
        gl.glVertex2f(7, -8);
        gl.glVertex2f(2, -9);
        gl.glVertex2f(2, -5);
        gl.glVertex2f(5, -4);

        gl.glEnd();

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {    
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();  
        
        //evita a divisão por zero
        if(height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;
        
        //seta o viewport para abranger a janela inteira
        gl.glViewport(0, 0, width, height);
                
        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);      
        gl.glLoadIdentity(); //lê a matriz identidade
        
        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
        if(width >= height)            
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else        
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
                
        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }    
       
    @Override
    public void dispose(GLAutoDrawable drawable) {}         
}
