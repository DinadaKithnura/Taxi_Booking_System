package register;

import p1.LoginUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controller {
    private model model;
    private view view;

    public controller(model model, view view) {
        this.model = model;
        this.view = view;

        this.view.addRegisterListener(new RegisterListener());

        this.view.addClearListener(new ClearListener()); 

        this.view.addBackListener(new BackListener());
                     
    }
    
    class RegisterListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		 String name = view.getNameField();
    		 String email = view.getEmailField();
    		 String city = view.getCityField();
    		 String password = view.getPasswordField();
    		 String confirmPassword = view.getConfirmPasswordField();
    		 
    		 model.setName(name);
    		 model.setEmail(email);
    		 model.setCity(city);
    		 model.setPassword(password);
    		 model.setConfirmPassword(confirmPassword);
    		 
    		 if (model.registerUser() ) {
    			 view.clearForm();
    		 }
    	}
    }

    class ClearListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		 view.clearForm();
    	}
    }
    
    class BackListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		new LoginUI();
            view.dispose(); 
    	}
    }   
        
}
