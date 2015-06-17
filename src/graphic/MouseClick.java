package graphic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClick extends MouseAdapter {

	public static int x = -1;
	public static int y = -1;
	
	public void mouseClicked(MouseEvent e){
		//System.out.println(e.getPoint());
		x = e.getPoint().x;
		y = e.getPoint().y;
	}

}
