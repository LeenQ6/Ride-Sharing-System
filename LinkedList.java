public class LinkedList<T>{
private Node<T> head ; 
private Node<T> current ; 
private int size ; 

public LinkedList(){
head = current = null ; 
}

public boolean empty(){
return head == null ;
}

public boolean last(){
return current.next == null ; 
}

public boolean full(){
return false ;
}

public void findfirst(){
current = head ;
}

public void findnext(){
current = current.next ;
}

public T retrieve(){
return current.data ;
}

public void update(T d){
current.data = d ;
}

public void insert(T d){
Node<T> newNode = new Node<T> (d) ; 

if (head == null){
head = newNode ; 
current = newNode ; 
}
else{
newNode.next = current.next ; 
current.next = newNode ;

current = newNode ; 
}
}

public void remove(){
if (current == head){
head = head.next ; 
current = current.next ; 
}
else{
Node<T> temp = head ; 
while (temp.next!= current)
temp = temp.next ; 

temp.next = current.next ; 
if (current.next!=null)
current = current.next  ; 
else
current = head ; 
}
}

public int size(){
return size ; 
}

public void display(){
if (head == null)
return ; 

Node<T> temp = head ; 

while ( temp!= null){
System.out.println(temp.data) ; 
temp = temp.next ; 
}

System.out.println(size) ;
}

public void insertBeforeCurrent(T val){
Node<T> newNode = new Node<T> () ;
if (size == 0)
head = current = newNode ; 
else if( current == head){
newNode.next = head ; 
head = newNode ; 
}
else{
Node<T> temp = head ; 

while ( temp.next!= current)
temp = temp.next ;

newNode.next = current ; 
temp.next = newNode ; 
}
size++ ; 
}


}
