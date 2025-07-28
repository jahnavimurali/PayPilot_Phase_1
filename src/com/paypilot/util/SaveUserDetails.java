	
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.paypilot.model.User;

public class SaveUserDetails {

    public void saveUserDetails(User[] users) {
    	
        try (FileOutputStream fileOutputStream = new FileOutputStream("User.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
