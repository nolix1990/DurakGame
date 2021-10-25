package Model;


import javax.swing.ImageIcon;

public class Card {
		private int number;
		private Shape shape;
		
		//constructor
		public Card(int number, Shape shape) {
			this.number = number;
			this.shape = shape;
		}
		
		/**
		 * function that retunrs card number
		 * @return card number
		 */
		public int getNumber() {
			return number;
		}
		
		/**
		 * function that sets number to card
		 * @param number
		 */
		public void setNumber(int number) {
			this.number = number;
		}
		
		/**
		 * function that gets card shape
		 * @return shape of card
		 */
		public Shape getShape() {
			return shape;
		}

		/**
		 * @return image icon of card
		 */
		public ImageIcon getImageIcon(){
			ImageIcon ico = new ImageIcon("Cards/"+this.number+"_of_"+this.shape.name()+".png");
			return ico;
		}
		
		/**
		 * @return image icon with side card
		 */
		public ImageIcon getSideImage(){
			ImageIcon ico = new ImageIcon("Cards/Side/"+getNumber()+"_of_"+shape.name()+".png");
			return ico;
		}
		
		/**
		 * @return - image backside card
		 */
		public ImageIcon getBackImage(){
			ImageIcon ico = new ImageIcon("Cards/backside.png");
			return ico;
		}
		
		/**
		 * @return  - string ,the path to image
		 */
		@Override
		public String toString() {
			String str;
			str="Cards/"+this.number+"_of_"+this.shape.name()+".png";
			return str;
		}
}