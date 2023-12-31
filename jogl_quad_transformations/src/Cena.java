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

        //Quadrado e suas transformações

        //transladar para canto superior direito
        gl.glColor3f(0,1,0);
        gl.glPushMatrix(); // armazena transformações
        gl.glTranslatef(7, 5, 0); // transformação
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(4, 0);
        gl.glVertex2f(4,4);
        gl.glVertex2f(0, 4);
        gl.glEnd();
        gl.glPopMatrix(); // retira as transformações

        //transladar para canto inferior esquerdo
        gl.glColor3f(1,1,0);
        gl.glPushMatrix();
        gl.glTranslatef(-7, -5, 0); // transformação
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(4, 0);
        gl.glVertex2f(4,4);
        gl.glVertex2f(0, 4);
        gl.glEnd();
        gl.glPopMatrix();

        //rotacionar 45º em relação ao ponto z
        gl.glColor3f(1,0,1);
        gl.glPushMatrix();
        gl.glRotatef(45, 0, 0, 1); // transformação
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(4, 0);
        gl.glVertex2f(4,4);
        gl.glVertex2f(0, 4);
        gl.glEnd();
        gl.glPopMatrix();

        //aumentar a escala
        gl.glColor3f(1,1,1);
        gl.glPushMatrix();
        gl.glScalef(2, 2, 0); // transformação
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(4, 0);
        gl.glVertex2f(4,4);
        gl.glVertex2f(0, 4);
        gl.glEnd();
        gl.glPopMatrix();

        //diminuir a escala
        gl.glColor3f(0,1,1);
        gl.glPushMatrix();
        gl.glScalef(0.5f, 0.5f, 0); // transformação
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(4, 0);
        gl.glVertex2f(4,4);
        gl.glVertex2f(0, 4);
        gl.glEnd();
        gl.glPopMatrix();

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
