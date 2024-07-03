import org.example.Frame;
import org.example.IGenerateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class FrameTest {
    private Frame frame;
    private IGenerateur generateur;
    private boolean lastFrame;

   @BeforeEach
    public void setUp() {
       generateur = mock(IGenerateur.class);
   }

   @Test
    public void Roll_SimpleFrame_FirstRoll_CheckScore() {
       //Arrange
       lastFrame = false;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(5);

       //Act
    frame.makeRoll();
       //Assert
       Assertions.assertEquals(5,frame.getScore());
   }

   @Test
    public void Roll_SimpleFrame_SecondRoll_CheckScore(){
       //Arrange
       lastFrame = false;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(5,3);

       //Act
       frame.makeRoll();
       frame.makeRoll();
       int result = frame.getScore();

       //Assert
       Assertions.assertEquals(8,result);

   }

   @Test
    public void Roll_SimpleFrame_SecondRoll_FirstRollStrick_ReturnFalse(){
       //Arrange
       lastFrame = false;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(10);

       //Act
       boolean result = frame.makeRoll();

       //Assert
       Assertions.assertFalse(result);
   }

   @Test
    public void Roll_SimpleFrame_MoreTwoRolls_ReturnFalse (){
       //Arrange
       lastFrame = false;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(5,1);
       //Act
       frame.makeRoll();
       boolean result = frame.makeRoll();
       //Assert
       Assertions.assertFalse(result);

   }

   @Test
    public void Roll_LastFrame_SecondRoll_FirstRollStrick_ReturnTrue(){
       //Arrange
       lastFrame = true;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(10);
       //Act
       boolean result = frame.makeRoll();
       //Assert
       Assertions.assertTrue(result);
   }
   @Test
    public void Roll_LastFrame_SecondRoll_FirstRollStrick_CheckScore(){
       //Arrange
       lastFrame = true;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(10,5);

       //Act
       frame.makeRoll();
       frame.makeRoll();
       int result = frame.getScore();


       //Assert
       Assertions.assertEquals(15, result);
   }
   @Test
    public void Roll_LastFrame_ThirdRoll_FirstRollStrick_ReturnTrue(){
       //Arrange
       lastFrame = true;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(10,5);

       //Act

       frame.makeRoll();
       boolean result = frame.makeRoll();

       //Assert
       Assertions.assertTrue(result);
   }
   @Test
    public void Roll_LastFrame_ThirdRoll_FirstRollStrick_CheckScore(){
       //Arrange
       lastFrame = true;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(10,5,8);
       //Act
       frame.makeRoll();
       frame.makeRoll();
       frame.makeRoll();
       int result = frame.getScore();
       //Assert
       Assertions.assertEquals(result,23);
   }

   @Test
    public void Roll_LastFrame_ThirdRoll_Spare_ReturnTrue(){
       //Arrange
       lastFrame = true;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(2,8);

       //Act
       frame.makeRoll();
       boolean result = frame.makeRoll();

       //Assert
       Assertions.assertTrue(result);
   }
   @Test
    public void Roll_LastFrame_ThirdRoll_Spare_CheckScore(){
       //Arrange
       lastFrame = true;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(2,2,3);
       //Act
       frame.makeRoll();
       frame.makeRoll();
       frame.makeRoll();
       int result = frame.getScore();
       //Assert
       Assertions.assertEquals(result,7);
   }
   @Test
    public void Roll_LastFrame_FourthRoll_ReturnFalse(){
       //Arrange
       lastFrame = false;
       frame = new Frame(generateur,lastFrame);
       when(generateur.randomPin(anyInt())).thenReturn(2,3,4,1);
       //Act
       frame.makeRoll();
       frame.makeRoll();
       frame.makeRoll();
       boolean result = frame.makeRoll();
       //Assert
       Assertions.assertFalse(result);
   }




}
