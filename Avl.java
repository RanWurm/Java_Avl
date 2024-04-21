public class Avl{
    private int rootVal;
    private int height = 1;
    private int balanceCoEfficient = 0;
    private Avl leftAvlSubTree = null;
    private Avl rightAvlSubTree = null;
    private Avl parent = null;

    public Avl(int val) {
        this.rootVal = val;
    }
    public void setParent(Avl parent) {
        this.parent = parent;
    }
    public void setRootVal(int rootVal) {
        this.rootVal = rootVal;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setBalanceCoEfficient(int balanceCoEfficient) {
        this.balanceCoEfficient = balanceCoEfficient;
    }

    public void setLeftAvlSubTree(Avl leftAvlSubTree) {
        this.leftAvlSubTree = leftAvlSubTree;
    }
    public void setRightAvlSubTree(Avl rightAvlSubTree) {
        this.rightAvlSubTree = rightAvlSubTree;
    }

    public int getBalanceCoEfficient() {
        return balanceCoEfficient;
    }

    public Avl getLeftAvlSubTree() {
        return leftAvlSubTree;
    }
    public Avl getRightAvlSubTree() {
        return rightAvlSubTree;
    }
    public Avl getParent() {
        return parent;
    }
    public int getRootVal() {
        return rootVal;
    }
    public int getHeight() {
        return height;
    }


    private boolean treeIsBalanced(){
        if(Math.abs(this.balanceCoEfficient) <= 1){
            return true;
        }
        return false;
    }
    public Avl insert(int val){
        if (isInTree(val)){
            return this;
        }
        Avl toInsert = new Avl(val);
         return insert(toInsert);
    }
    public void inOrder(){
        if(this.leftAvlSubTree != null){
            leftAvlSubTree.inOrder();
        }
        System.out.println(this.rootVal);
        if(this.rightAvlSubTree != null){
            rightAvlSubTree.inOrder();
        }

    }

    private boolean isLeaf(){
        if(this.leftAvlSubTree == null && this.rightAvlSubTree == null){
            return true;
        }
        return false;
 }
    public Boolean isInTree(int val){
        if(this.rootVal == val){
            return true;
        }
        if(this.rootVal> val){
            if(this.leftAvlSubTree == null){
                return false;
            } else {
                this.leftAvlSubTree.isInTree(val);
            }
        }
        if(this.rootVal < val){
            if(this.rightAvlSubTree == null){
                return false;
            } else {
                this.rightAvlSubTree.isInTree(val);
            }
        }
        return false;
    }

    private boolean haveLeftSon(){
        if(this.leftAvlSubTree != null){
            return true;
        }
        return false;
    }
    private boolean haveRightSon(){
        if(this.rightAvlSubTree != null){
            return true;
        }
        return false;
    }
    private Avl createNewCopy(Avl t){
        if (t == null){
            return null;
        }
        if(t.getLeftAvlSubTree() !=null){

        }
        Avl toReturn = new Avl(t.getRootVal());

        toReturn.setParent(t.getParent());
        toReturn.setHeight(t.getHeight());
        toReturn.setBalanceCoEfficient(t.getBalanceCoEfficient());
        toReturn.setLeftAvlSubTree(t.getLeftAvlSubTree());
        toReturn.setRightAvlSubTree(t.getRightAvlSubTree());

        return toReturn;
    }
    private Avl createCopy(Avl t){
        if (t == null){
            return null;
        }
        Avl toReturn = new Avl(t.getRootVal());
        toReturn.setHeight(t.getHeight());
        toReturn.setBalanceCoEfficient(t.getBalanceCoEfficient());
        return toReturn;
    }
    private void copy(Avl target, Avl source){
        target.setRootVal(source.getRootVal());
        target.setParent(source.getParent());
        target.setHeight(source.getHeight());
        target.setBalanceCoEfficient(source.getBalanceCoEfficient());
        target.setLeftAvlSubTree(source.getLeftAvlSubTree());
        target.setRightAvlSubTree(source.getRightAvlSubTree());

    }
    private void setBalanceCoEfficient() {
        int leftHeight = 0;
        int rightHeight = 0;
        if(this.isLeaf()){
            setBalanceCoEfficient(0);
            return;
        }
        if(this.haveLeftSon()){
            leftHeight = this.getLeftAvlSubTree().getHeight();
        }
        if(this.haveRightSon()){
            rightHeight = this.getRightAvlSubTree().getHeight();
        }
        this.setBalanceCoEfficient(leftHeight - rightHeight);
    }
    private Avl getChoppedSubTree(Avl t){
        if(t == null){
            return null;
        }
        Avl chopped = createNewCopy(t);
        chopped.setParent(null);
        return cloneTree(chopped);

    }
    private Avl cloneTree(Avl root) {
        if (root == null) {
            return null;
        }
        if (root.isLeaf()) {
            return createCopy(root);
        }
        Avl left = null;
        Avl right = null;
        Avl nRoot = createCopy(root);
        if (root.haveLeftSon()) {
            left = cloneTree(root.getLeftAvlSubTree());
            left.setParent(nRoot);
        }
        if (root.haveRightSon()) {
            right = cloneTree(root.getRightAvlSubTree());
            right.setParent(nRoot);
        }
        nRoot.setLeftAvlSubTree(left);
        nRoot.setRightAvlSubTree(right);

        return nRoot;
    }
    private void LR(Avl rightSubTree){
        Avl leftRightSubTree = rightSubTree.getLeftAvlSubTree();
        if(leftRightSubTree.isLeaf()){
            rightSubTree.getParent().setRightAvlSubTree(leftRightSubTree);
            leftRightSubTree.setParent(rightSubTree.getParent());
            leftRightSubTree.setRightAvlSubTree(rightSubTree);
            rightSubTree.setParent(leftRightSubTree);
            rightSubTree.setLeftAvlSubTree(null);
        } else {
            rightSubTree.RR();
        }


    }
    private void RL(Avl leftSubTree){
        Avl rightLeftSubTree= leftSubTree.getRightAvlSubTree();
        if(rightLeftSubTree.isLeaf()){
            leftSubTree.getParent().setLeftAvlSubTree(rightLeftSubTree);
            rightLeftSubTree.setParent(leftSubTree.getParent());
            rightLeftSubTree.setLeftAvlSubTree(leftSubTree);
            leftSubTree.setParent(rightLeftSubTree);
            leftSubTree.setRightAvlSubTree(null);
        } else {
            leftSubTree.LL();
        }
    }
    private void fixAvlTree(){

        if(treeIsBalanced()){
            return;
        }
        if(balanceCoEfficient == -2){
            if(this.rightAvlSubTree.balanceCoEfficient == 1){
                LR(this.rightAvlSubTree);
            }
            LL();
        }
        if(balanceCoEfficient == 2){
            if(this.leftAvlSubTree.balanceCoEfficient == -1){
                RL(this.leftAvlSubTree);
            }
            RR();
        }
        setBalanceCoEfficient();
    }
    protected Avl insert(Avl toInsert){
        Avl nRoot = cloneTree(this);
        Avl tParent = nRoot;
        Avl curr = nRoot;
        while(curr != null){
            if(toInsert.getRootVal() > tParent.getRootVal()){
                tParent= curr;
                curr = curr.getRightAvlSubTree();
            } else {
                tParent= curr;
                curr = curr.getLeftAvlSubTree();
            }
        }
        if(toInsert.getRootVal() > tParent.getRootVal()){
            tParent.setRightAvlSubTree( toInsert);
            toInsert.setParent(tParent);
        }
        if(toInsert.getRootVal() < tParent.getRootVal()){
            tParent.setLeftAvlSubTree( toInsert);
            toInsert.setParent(tParent);
        }
        setHeight(toInsert);
        return nRoot;
    }
    private void setHeight(Avl source) {
        if(source == null){
            return;
        }
        Avl curr = source.getParent();
        if(source.isLeaf()){
            source.setHeight(1);
        }
        while(curr != null) {

            int leftHeight = 0;
            int rightHeight = 0;
            if (curr.leftAvlSubTree != null) {
                leftHeight = curr.leftAvlSubTree.getHeight();
            }
            if (curr.rightAvlSubTree != null) {
                rightHeight = curr.rightAvlSubTree.getHeight();
            }
            curr.setHeight(Math.max(leftHeight, rightHeight) + 1);
            curr.setBalanceCoEfficient();
            if(!curr.treeIsBalanced()){
                curr.fixAvlTree();
            }
            curr = curr.getParent();
        }

    }
    private void RR(){
        Avl oldRoot = getChoppedSubTree(this);
        Avl nRoot = getChoppedSubTree(this.getLeftAvlSubTree());
        Avl rightNRoot = this.getLeftAvlSubTree().getRightAvlSubTree();
        Avl leftNRoot = nRoot.getLeftAvlSubTree();
        Avl curParent = this.getParent();
        if(leftNRoot != null){
            leftNRoot.setBalanceCoEfficient();
            setHeight(leftNRoot);
            if(curParent == null){
                leftNRoot.setParent(this);
            }
        }

        oldRoot.setLeftAvlSubTree(rightNRoot);
        if(rightNRoot != null){
            rightNRoot.setBalanceCoEfficient();
            rightNRoot.setParent(oldRoot);
            setHeight(rightNRoot);
        }

        nRoot.setRightAvlSubTree(oldRoot);
        oldRoot.setBalanceCoEfficient();
        oldRoot.setParent(nRoot);
        setHeight(oldRoot);
        nRoot.setBalanceCoEfficient();
        if(curParent == null){
            oldRoot.setParent(this);
        } else {
            curParent.setLeftAvlSubTree(nRoot);
            nRoot.setParent(curParent);
            if(rightNRoot != null){
                rightNRoot.setParent(nRoot);
            }
        }
        setHeight(nRoot);
        copy(this,nRoot);
    }
    private void LL(){
        Avl oldRoot = getChoppedSubTree(this);
        Avl nRoot = getChoppedSubTree(this.getRightAvlSubTree());
        Avl rightNRoot = nRoot.getRightAvlSubTree();
        Avl leftNRoot = this.getRightAvlSubTree().getLeftAvlSubTree();
        Avl curParent = this.getParent();


        if(rightNRoot != null){
            rightNRoot.setBalanceCoEfficient();
            setHeight(rightNRoot);
            if(curParent == null){
                rightNRoot.setParent(this);
            }
        }

        oldRoot.setRightAvlSubTree(leftNRoot);
        if(leftNRoot != null){
            leftNRoot.setBalanceCoEfficient();
            leftNRoot.setParent(oldRoot);
            setHeight(leftNRoot);
        }

        nRoot.setLeftAvlSubTree(oldRoot);
        oldRoot.setBalanceCoEfficient();
        oldRoot.setParent(nRoot);
        setHeight(oldRoot);
        nRoot.setBalanceCoEfficient();
        if(curParent == null){
            oldRoot.setParent(this);
        } else {
            curParent.setRightAvlSubTree(nRoot);
            nRoot.setParent(curParent);
            if(leftNRoot != null){
                leftNRoot.setParent(nRoot);
            }
        }
        setHeight(nRoot);
        copy(this,nRoot);
    }

    public static void testRR(){
        Avl tmp = new Avl(50);
        tmp = tmp.insert(40);
        tmp = tmp.insert(30);
        tmp = tmp.insert(25);
        tmp = tmp.insert(20);
        tmp = tmp.insert(15);
        tmp = tmp.insert(10);
        tmp = tmp.insert(9);
        tmp = tmp.insert(8);
        tmp = tmp.insert(7);
        tmp = tmp.insert(6);
        tmp = tmp.insert(5);
        tmp.inOrder();
    }
    public static void testLL(){
        Avl tmp = new Avl(5);
        tmp = tmp.insert(6);
        tmp = tmp.insert(7);
        tmp = tmp.insert(8);
        tmp = tmp.insert(9);
        tmp = tmp.insert(10);
        tmp = tmp.insert(15);
        tmp = tmp.insert(20);
        tmp = tmp.insert(25);
        tmp = tmp.insert(30);
        tmp = tmp.insert(40);
        tmp = tmp.insert(50);
        tmp.inOrder();
    }

    public static void testLR(){
        Avl tmp = new Avl(50);
        tmp = tmp.insert(70);
        tmp = tmp.insert(60);
        tmp = tmp.insert(55);
        tmp.inOrder();
    }

    public static void testRL(){
        Avl tmp = new Avl(50);
        tmp = tmp.insert(30);
        tmp = tmp.insert(40);
        tmp = tmp.insert(45);
        tmp.inOrder();
    }

    public static void main(String[] args) {
        //testRR();
        //testLL();
        //testLR();
        //testRL();

    }

}


