import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Simay_Ayberik_2019510018 {
	
	 // Dynamic approach method , uses find_exclude , find include helper methods
	// Bottom-up , recursive approach is used
	// Considers each lion as a root of tree and calculates max possible total hunting_power with the selected root's subtree
	// Calculates two different data: max possible total power if the lion is included or excluded
	// Saves excluded and included versions of each lion as node's attributes and stores all in n-ary tree
	// if the lions's excluded or included value is already calculated and saved into node, it gets data and no calculation for that node is executed
	  public static int DP(Node node) {
			 
			 int max_hunting_ability=Math.max(find_exclude(node),find_include(node));
		      return max_hunting_ability;  
				
			}
	 public static int Greedy(Node root,int max_hunting) {
		
		 if(root!=null) {
			 if(findGrandChildrensum(root)+root.power>Childrensum(root)) {
				 max_hunting+=findGrandChildrensum(root)+root.power;
				 if(root.child!=null) {
					 Greedy(root.child,max_hunting);
				 }
				 
			 }
			 else {
				 max_hunting+=Childrensum(root);
				 Greedy(root.child,max_hunting);
			 }
			 while(root.next!=null) {
				 Greedy( root.next,max_hunting);
				 root=root.next;
			 }
			 
		 }
	       	 
			
		 
		return max_hunting;
		
	
	}
	
	 // helper methods to execute function DP 
	 // starts each node's excluded and included value as '0' which kept at node structure
	 // finds lion's and its subtrees max_power if lion is not included
	 public static int find_exclude(Node root) {
    	 int exclude_sum=0;
   	  if(root!=null) {
   		  if(root.child!=null) {
   			  root=root.child;
   			  // if node has its exclude value as '0', it calculates its excluded recursion and saves to node's attribute to not calculate again
   			  if(root.ex==0) {   
   				root.ex=find_exclude(root);
   			  }
   			  
   			 if(root.inc==0) {
   				root.inc=find_include(root);
   			  }
   			
   			  //  adds and selects max of the node's child's excluded or included value to exclude_sum
   			 // if exclude and include data's of node is already calculated in ex recursives, it gets data from node
   			  exclude_sum+=Math.max(root.ex,root.inc);
   			 
   				 while(root.next!=null) {
   					 // calculates child's sibling's ex and inc values if it is not calculated before
   					if(root.next.ex==0) {
   		   				root.next.ex=find_exclude(root.next);
   		   			  }
   		   			  
   		   			 if(root.next.inc==0) {
   		   				root.next.inc=find_include(root.next);
   		   			  }
   		   			 	 
   		   			  exclude_sum+=Math.max(root.next.ex,root.next.inc);
   					 root=root.next;
   				 }
   		  }
   	  }
   	return exclude_sum;
       	 
		
    	 
     }
	 // finds lion's and its subtrees max_power if lion is included
	 public static int find_include(Node root) {

    	 int include_sum=0;
    	 // if included value is not kept in node, adds lion' hunting power to included_sum
    	 if(root.inc==0) {
    		 include_sum+=root.power;
    	 }
    	 
    	 if(root!=null) {
      		  if(root.child!=null) {
      			  root=root.child;
      			  if(root.ex==0) {
      				  root.ex=find_exclude(root);
      			  }
      			include_sum+=root.ex;
      				 while(root.next!=null) {
      					if(root.next.ex==0) {
            				  root.next.ex=find_exclude(root.next);
            			  }
      					include_sum+=root.next.ex;
      					 root=root.next;
      				 }
      		  }
      	  }
    	
    	 
 		return include_sum;
     	 
      }
	// starts each node's excluded and included value as '0' which kept at node structure
	 static class Node
	    {
	        String lion;
	        int power;
	        int ex=0;
	        int inc=0;
	        Node next, child, parent;
	        public Node(String lion)
	        {
	            this.lion = lion;
	            next = child = parent = null;
	        }
	    }
     public static void displayLions(Node node) {
    	 
    	 if(node.inc>node.ex) {
    		 System.out.print(node.lion + " ");
    		 if(node.child!=null) {
        		 node=node.child;
      			  }
        	 while(node.next!=null) {

    				node=node.next;
    			 }
    	 }
    	 
    	
  					 
  			
  			 
    	 
     }
	 
	   
	    // Traverses tree in depth first order
	    static public void traverseTree(Node root)
	    {
	        if(root == null)
	            return;
	        while(root != null)
	        {
	            System.out.print(root.lion + " ");
	            if(root.child != null)
	            	
	                traverseTree(root.child);
	            root = root.next;
	        }
	    }
	  
	  // finds the root of n-ary tree with given any input node
     static public Node findRoot(Node root) {
         while(root.parent!=null) {
        	 root=root.parent;
         }   	
 		return root;
           
     }
     // sums grandchildren and its all siblings for any node
     static public int findGrandChildrensum(Node root1) {
    	 int grandc_sum=0;
    	 if(root1!=null) {
   		  if(root1.child!=null) {
   			  root1=root1.child;
   			grandc_sum+=Childrensum(root1);
   			while(root1.next!=null) {
   				grandc_sum+=Childrensum(root1.next);
				  root1=root1.next;
			 }
     			 
     		  }
   		  }
		return grandc_sum;
    	 
     }
     // sums children and its all siblings for any node
     static public int Childrensum(Node root1) {


	  int children_sum=0;
	  if(root1!=null) {
		  if(root1.child!=null) {
			  root1=root1.child;
				 children_sum+=root1.power;
				 while(root1.next!=null) {
					
					 children_sum+=root1.next.power;
					 root1=root1.next;
				 }
		  }
	  }
	return children_sum;
    	 
     }

     
     
	public static void main(String[] args) throws FileNotFoundException {
		
		     ArrayList lion_list=new ArrayList();
		     ArrayList power_list=new ArrayList();
		     ArrayList<Node> nodes=new ArrayList();
		     int count=0;
		     int max_hunting=0;  
	         File file = new File("hunting_abilities.txt");
	         File file2 = new File("lions_hierarchy.txt");
	         Scanner sc = new Scanner(file);
	         Scanner sc2 = new Scanner(file2);
			  // split hunting abilities and creating node with hunting ability included 
	         while (sc.hasNextLine()) {
	 			String[] splitted = sc.nextLine().split("\t");
                if(count!=0) {
                	lion_list.add(splitted[0]);
    	 			power_list.add(splitted[1]);
    	 			Node rootie=new Node(splitted[0]);
    	 			int pow=Integer.parseInt(splitted[1]); 
    	 			rootie.power=pow;
    	 			nodes.add(rootie);
	 			}
	 			 			
	 			count++;
	 		}
	         count=0;
	         
	         // split hunting hierarchy of lions and connect the parent,child, sibling hierarchy with search of all nodes
	         // takes 0(n^2) time for the search
	         while (sc2.hasNextLine()) {
	        	 String[] splitted = sc2.nextLine().split("\t");
	        	 if(count!=0) {
	        		 for(int i=0;i<nodes.size();i++) {
			 				if(splitted[0].equalsIgnoreCase(nodes.get(i).lion.toString())) {
			 					
			 					for(int j=0;j<nodes.size();j++) {
					 				if(splitted[1].equalsIgnoreCase(nodes.get(j).lion.toString())) {
					 					if(splitted[2].equalsIgnoreCase("Left-Child")) {
							 				nodes.get(i).child=nodes.get(j);
							 				nodes.get(j).parent=nodes.get(i);
							 			}
					 					else {
					 						nodes.get(i).next=nodes.get(j);
					 						nodes.get(j).parent=nodes.get(i).parent;
					 					}
					 				}
					 			}
			 					
			 				}
			 			}
		 			}
		 			 			
		 			count++;		
		 		}
	         System.out.println("DP Results: " + DP(findRoot(nodes.get(1))));
	         displayLions(findRoot(nodes.get(1)));
	         System.out.println("Greedy Results: " + Greedy(findRoot(nodes.get(1)), max_hunting) );
	          
	}

}
