/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package iaproyecto1;

import java.awt.Color;
import java.awt.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import static iaproyecto1.AStar.searchPath;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Daniel
 */
public class UIRush extends javax.swing.JFrame {

    
    LinkedList<Vehicle> objectArrayVehiculo = new LinkedList<>();
    LinkedList<State> path = new LinkedList<>();
    int pathIndex = 0;
    LinkedList<String> nombreVehiculos = new LinkedList<>();
    Color[] colores = {Color.red,Color.pink,Color.green,Color.yellow,Color.black,Color.orange,Color.magenta,new Color(204,204,204),new Color(153,153,255),new Color(204,153,255),new Color(255,102,153),new Color(255,255,153),new Color(204,255,153),new Color(153,255,153),new Color(153,255,204),new Color(0,153,153),new Color(102,0,102)};
    String posInicial = "";
    String posFinal = "";
    Boolean seleccionando=true;
    Boolean boolPosInicial=true;
    Boolean boolPosFinal=false;
    String posSalida="";
    int totalCars=1;
    int totalTrucks=0;
    

    
    /**
     * Creates new form UIRush
     */
    public UIRush() {
        initComponents();
    }

    public void getPosicionPanel(java.awt.event.MouseEvent evt, String pos) {
        //System.out.println(pos);
        if(objectArrayVehiculo.size()<(totalCars+totalTrucks+1)){
            JPanel labelSelect = getLabel(pos);
            labelSelect.setBackground(colores[objectArrayVehiculo.size()]);
            String wordTypeVehicle="";
            
            if(boolPosInicial){
                posInicial=pos;
                boolPosInicial=false;
                boolPosFinal=true;
                //if(objectArrayVehiculo.size()>0){
                    showMessageDialog(null, "Posicion final del " + nombreVehiculos.get(objectArrayVehiculo.size()) + ":");
                //}
            }
            else{
               posFinal=pos;
               boolPosFinal=false;
               boolPosInicial=true;
               if(objectArrayVehiculo.size()<(totalCars+totalTrucks)){
                   showMessageDialog(null, "Posicion inicial del " + nombreVehiculos.get(objectArrayVehiculo.size()+1) + ":");
               }
               else{
                   showMessageDialog(null,"Agregue la salida :");
               }
            }
            addCar();
        //System.out.println(evt.getComponent().get);
//        JPanel selectedButton = (JPanel) evt.getSource();
//        selectedButton.setBackground(Color.red);
        }
        else{
            showMessageDialog(null,"Agregue la salida :");
        }
    }

    public void addCar(){
        System.out.println(posInicial);
        System.out.println(posFinal);
        


        if(!posInicial.equals("") && !posFinal.equals("")){
            String wordTypeVehicle=nombreVehiculos.get(objectArrayVehiculo.size()).substring(0,3);
            Boolean isCar=true;
            System.out.println(wordTypeVehicle);

            Boolean horizontal=true;
            String[] posInicialArray = posInicial.split(",");
            String[] posFinalArray = posFinal.split(",");
            if(!posInicialArray[0].toString().equals(posFinalArray[0].toString())){
                horizontal=false;
            }
            int mayorInicial=Integer.parseInt(posInicialArray[0])+Integer.parseInt(posInicialArray[1]);
            int mayorFinal=Integer.parseInt(posFinalArray[0])+Integer.parseInt(posFinalArray[1]);
            if(wordTypeVehicle.equals("Cam")){
                isCar=false;
                String centerPos=getPosCenterTruck(Integer.parseInt(posInicialArray[0]),Integer.parseInt(posInicialArray[1]),Integer.parseInt(posFinalArray[0]),Integer.parseInt(posFinalArray[1]));
                JPanel labelSelect = getLabel(centerPos);
                labelSelect.setBackground(colores[objectArrayVehiculo.size()]);
            }
            int x;
            int y;
            if(mayorInicial>mayorFinal){
                x=Integer.parseInt(posFinalArray[0]);
                y=Integer.parseInt(posFinalArray[1]);
            }
            else{
                x=Integer.parseInt(posInicialArray[0]);
                y=Integer.parseInt(posInicialArray[1]);
            }
            Vehicle vehiculoObj = new Vehicle(objectArrayVehiculo.size(),true,isCar,colores[objectArrayVehiculo.size()],posInicial,posFinal, nombreVehiculos.get(objectArrayVehiculo.size()),horizontal,x,y);   
            objectArrayVehiculo.add(vehiculoObj);
            posInicial="";
            posFinal="";
        }
    }
    
    private String getPosCenterTruck(int x1,int y1,int x2,int y2){
        int midX = (x1 + x2)/2;
        int midY = (y1 + y2)/2;
        return (Integer.toString(midX)+","+Integer.toString(midY)); 
   }
    private static void print(LinkedList<State> path) {
        System.out.println("Number of optimal movements = " + (path.size()-1) + "\n");
        int index = 0;
        for(State s : path){
                if(index == 0)
                        System.out.println("Initial state:");
                else
                        System.out.println("Step " + index + ":");
                s.print();
                index ++;
        }
    }
    
    private void printState(State state){
        resetPanel();
        LinkedList<Vehicle> vehicles = state.getMap().vehicles;
        vehicles.forEach((vehicle) -> {
            int x = vehicle.x;
            int y = vehicle.y;
            JPanel labelSelect = getLabel(x+","+y);
            labelSelect.setBackground(vehicle.color);
            if(vehicle.isHorizontal()){
                if(vehicle.size == 2){
                    labelSelect = getLabel(x+","+(y+1));
                    labelSelect.setBackground(vehicle.color);
                }
                else if(vehicle.size == 3){
                    labelSelect = getLabel(x+","+(y+1));
                    labelSelect.setBackground(vehicle.color);
                    labelSelect = getLabel(x+","+(y+2));
                    labelSelect.setBackground(vehicle.color);
                }
            }
            else if(vehicle.isVertical()){
                if(vehicle.size == 2){
                    labelSelect = getLabel(x+1+","+y);
                    labelSelect.setBackground(vehicle.color);
                }
                else if(vehicle.size == 3){
                    labelSelect = getLabel(x+1+","+y);
                    labelSelect.setBackground(vehicle.color);
                    labelSelect = getLabel(x+2+","+y);
                    labelSelect.setBackground(vehicle.color);
                }
            }

        });
    }
    
    public void resetPanel(){
        for (int x = 0; x<6; x++){
            for (int y = 0; y<6; y++){
                JPanel labelSelect = getLabel(x+","+y);
                labelSelect.setBackground(new Color(240, 240, 240));
            }
        }
    }
    
    public void getPosicionSalida(java.awt.event.MouseEvent evt, String pos) {       
        backButton.setEnabled(true);     
        nextButton.setEnabled(true);
        
        JPanel selectedButton = (JPanel) evt.getSource();
        selectedButton.setBackground(Color.BLUE);
        posSalida=pos;
        showMessageDialog(null,"Elementos agregados correctamente.");
        /////////////////Temporal hasta que se pueda iniciar manualmente//////////////////////
        String[] posSalidaArray = posSalida.split(",");
        int[] goalPoint = {Integer.parseInt(posSalidaArray[0]), Integer.parseInt(posSalidaArray[1])};
        Map map = new Map(6, objectArrayVehiculo, goalPoint);
        long startTime = System.currentTimeMillis();
        path = searchPath(map);
        long endTime = System.currentTimeMillis();
	long timeTaken = endTime - startTime;
        jLabel2.setText("Total de pasos: "+ (path.size()-1));
        jLabel3.setText("Tiempo tomado: " + timeTaken + " ms");
        System.out.println("Solution using Heuristic.");
        System.out.println("#########################");
        print(path);
    }
    
    public void addDataManual() {
        //showMessageDialog(null, "Agregue el veh??culo principal222.");
        
        JLabel labelNameCarPrincipal=new JLabel("Principal:");
        labelNameCarPrincipal.setBounds(20, 55, 90, 22);
        labelNameCarPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jPanelVehiculos.add(labelNameCarPrincipal);
        
        JPanel panelPrincipal=new JPanel();
        panelPrincipal.setBounds(110,55,25,25);
        panelPrincipal.setBackground(Color.red);
        jPanelVehiculos.add(panelPrincipal);
        
        int valueCars = ((Integer) jSpinnerCar.getValue())-1;
        totalCars=valueCars;
        int valueTrucks=  (Integer) jSpinnerTruck.getValue();
        totalTrucks=valueTrucks;
        //System.out.println(valueCars);
        
        int yPosition=85;
        int count=1;
        nombreVehiculos.add("Principal: ");
        while(valueCars>0){
            nombreVehiculos.add("Carro: "+count);
            JLabel labelNameCar=new JLabel("Carro: "+count);
            labelNameCar.setBounds(20, yPosition, 90, 22);
            labelNameCar.setFont(new Font("Tahoma", Font.PLAIN, 18));
            jPanelVehiculos.add(labelNameCar);
            
            JPanel panelColor=new JPanel();
            panelColor.setBounds(110,yPosition,25,25);
            panelColor.setBackground(colores[count]);
            jPanelVehiculos.add(panelColor);
        
            count++;
            valueCars--;
            yPosition+=35;
        }
        yPosition=55;
        int countTruck=1;
        while(valueTrucks>0){
            nombreVehiculos.add("Cami??n: "+countTruck);
            JLabel labelNameCar=new JLabel("Cami??n: "+countTruck);
            labelNameCar.setBounds(350, yPosition, 90, 22);
            labelNameCar.setFont(new Font("Tahoma", Font.PLAIN, 18));
            jPanelVehiculos.add(labelNameCar);
            
            JPanel panelColor=new JPanel();
            panelColor.setBounds(440,yPosition,25,25);
            panelColor.setBackground(colores[count]);
            jPanelVehiculos.add(panelColor);
        
            count++;
            countTruck++;
            valueTrucks--;
            yPosition+=35;
        }
//        JPanel panelHab=new JPanel();
//        panelHab.setBounds(200,200,200,200); 
//        jPanelVehiculos.add(panelHab); 

//        addCars();
//        Boolean principal = true;
//        while(objectArrayVehiculo.length<5){
//            Vehiculo vehiculo = new Vehiculo(objectArrayVehiculo.length,
//            principal);
//            principal=false;
//        }
    }

    public void addCars() {
        if (posInicial.equals("") || posFinal.equals("")) {
            if (posInicial.equals("")) {
                addPosicionInicial("Vehiculo1");
                addCars();
            }
            if (posFinal.equals("")) {
                addPosicionInicial("Vehiculo1");
                addCars();
            }
        } else {
            //addCars();
        }
    }

    public void addPosicionInicial(String nameCar) {
        showMessageDialog(null, "Posicion inicial del " + nameCar + ":");
    }

    public void addPosicionFinal(String nameCar) {
        showMessageDialog(null, "Posicion final del " + nameCar + ":");
    }

    //Muestra los datos de los vehiculos en el tablero.
    public void showDataView() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel00 = new javax.swing.JPanel();
        jPanel01 = new javax.swing.JPanel();
        jPanel02 = new javax.swing.JPanel();
        jPanel03 = new javax.swing.JPanel();
        jPanel04 = new javax.swing.JPanel();
        jPanel05 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        salida0 = new javax.swing.JPanel();
        salida1 = new javax.swing.JPanel();
        salida2 = new javax.swing.JPanel();
        salida3 = new javax.swing.JPanel();
        salida4 = new javax.swing.JPanel();
        salida5 = new javax.swing.JPanel();
        salida11 = new javax.swing.JPanel();
        salida10 = new javax.swing.JPanel();
        salida9 = new javax.swing.JPanel();
        salida8 = new javax.swing.JPanel();
        salida7 = new javax.swing.JPanel();
        salida6 = new javax.swing.JPanel();
        jPanelVehiculos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinnerTruck = new javax.swing.JSpinner();
        jSpinnerCar = new javax.swing.JSpinner();
        backButton = new javax.swing.JLabel();
        nextButton = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonAddData = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("0,0");

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        jPanel00.setToolTipText("mkm");
        jPanel00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel00MouseClicked(evt);
            }
        });
        jPanel00.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel00KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel00Layout = new javax.swing.GroupLayout(jPanel00);
        jPanel00.setLayout(jPanel00Layout);
        jPanel00Layout.setHorizontalGroup(
            jPanel00Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel00Layout.setVerticalGroup(
            jPanel00Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel01.setAlignmentX(0.0F);
        jPanel01.setAlignmentY(0.0F);
        jPanel01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel01MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel01Layout = new javax.swing.GroupLayout(jPanel01);
        jPanel01.setLayout(jPanel01Layout);
        jPanel01Layout.setHorizontalGroup(
            jPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel01Layout.setVerticalGroup(
            jPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel02.setAlignmentX(0.0F);
        jPanel02.setAlignmentY(0.0F);
        jPanel02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel02MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel02Layout = new javax.swing.GroupLayout(jPanel02);
        jPanel02.setLayout(jPanel02Layout);
        jPanel02Layout.setHorizontalGroup(
            jPanel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel02Layout.setVerticalGroup(
            jPanel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel03.setAlignmentX(0.0F);
        jPanel03.setAlignmentY(0.0F);
        jPanel03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel03MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel03Layout = new javax.swing.GroupLayout(jPanel03);
        jPanel03.setLayout(jPanel03Layout);
        jPanel03Layout.setHorizontalGroup(
            jPanel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel03Layout.setVerticalGroup(
            jPanel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel04.setAlignmentX(0.0F);
        jPanel04.setAlignmentY(0.0F);
        jPanel04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel04MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel04Layout = new javax.swing.GroupLayout(jPanel04);
        jPanel04.setLayout(jPanel04Layout);
        jPanel04Layout.setHorizontalGroup(
            jPanel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel04Layout.setVerticalGroup(
            jPanel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel05.setAlignmentX(0.0F);
        jPanel05.setAlignmentY(0.0F);
        jPanel05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel05MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel05Layout = new javax.swing.GroupLayout(jPanel05);
        jPanel05.setLayout(jPanel05Layout);
        jPanel05Layout.setHorizontalGroup(
            jPanel05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel05Layout.setVerticalGroup(
            jPanel05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel15.setAlignmentX(0.0F);
        jPanel15.setAlignmentY(0.0F);
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel25.setAlignmentX(0.0F);
        jPanel25.setAlignmentY(0.0F);
        jPanel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel35.setAlignmentX(0.0F);
        jPanel35.setAlignmentY(0.0F);
        jPanel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel45.setAlignmentX(0.0F);
        jPanel45.setAlignmentY(0.0F);
        jPanel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel45MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel55.setAlignmentX(0.0F);
        jPanel55.setAlignmentY(0.0F);
        jPanel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel55MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel30MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel40MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel50MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel51MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel52MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel53MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel54MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel41MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel42MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel43MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel44MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel31MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel32MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel33MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel22MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel23MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        salida0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida0MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida0Layout = new javax.swing.GroupLayout(salida0);
        salida0.setLayout(salida0Layout);
        salida0Layout.setHorizontalGroup(
            salida0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        salida0Layout.setVerticalGroup(
            salida0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        salida1.setPreferredSize(new java.awt.Dimension(10, 0));
        salida1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida1Layout = new javax.swing.GroupLayout(salida1);
        salida1.setLayout(salida1Layout);
        salida1Layout.setHorizontalGroup(
            salida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        salida1Layout.setVerticalGroup(
            salida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        salida2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida2Layout = new javax.swing.GroupLayout(salida2);
        salida2.setLayout(salida2Layout);
        salida2Layout.setHorizontalGroup(
            salida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        salida2Layout.setVerticalGroup(
            salida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        salida3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida3Layout = new javax.swing.GroupLayout(salida3);
        salida3.setLayout(salida3Layout);
        salida3Layout.setHorizontalGroup(
            salida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        salida3Layout.setVerticalGroup(
            salida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        salida4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida4Layout = new javax.swing.GroupLayout(salida4);
        salida4.setLayout(salida4Layout);
        salida4Layout.setHorizontalGroup(
            salida4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        salida4Layout.setVerticalGroup(
            salida4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        salida5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida5Layout = new javax.swing.GroupLayout(salida5);
        salida5.setLayout(salida5Layout);
        salida5Layout.setHorizontalGroup(
            salida5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        salida5Layout.setVerticalGroup(
            salida5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        salida11.setPreferredSize(new java.awt.Dimension(74, 10));
        salida11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida11Layout = new javax.swing.GroupLayout(salida11);
        salida11.setLayout(salida11Layout);
        salida11Layout.setHorizontalGroup(
            salida11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        salida11Layout.setVerticalGroup(
            salida11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        salida10.setPreferredSize(new java.awt.Dimension(74, 10));
        salida10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida10Layout = new javax.swing.GroupLayout(salida10);
        salida10.setLayout(salida10Layout);
        salida10Layout.setHorizontalGroup(
            salida10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        salida10Layout.setVerticalGroup(
            salida10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        salida9.setPreferredSize(new java.awt.Dimension(74, 10));
        salida9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida9Layout = new javax.swing.GroupLayout(salida9);
        salida9.setLayout(salida9Layout);
        salida9Layout.setHorizontalGroup(
            salida9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        salida9Layout.setVerticalGroup(
            salida9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        salida8.setMinimumSize(new java.awt.Dimension(74, 10));
        salida8.setPreferredSize(new java.awt.Dimension(74, 10));
        salida8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida8Layout = new javax.swing.GroupLayout(salida8);
        salida8.setLayout(salida8Layout);
        salida8Layout.setHorizontalGroup(
            salida8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        salida8Layout.setVerticalGroup(
            salida8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        salida7.setPreferredSize(new java.awt.Dimension(74, 10));
        salida7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida7Layout = new javax.swing.GroupLayout(salida7);
        salida7.setLayout(salida7Layout);
        salida7Layout.setHorizontalGroup(
            salida7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        salida7Layout.setVerticalGroup(
            salida7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        salida6.setPreferredSize(new java.awt.Dimension(74, 10));
        salida6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salida6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salida6Layout = new javax.swing.GroupLayout(salida6);
        salida6.setLayout(salida6Layout);
        salida6Layout.setHorizontalGroup(
            salida6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        salida6Layout.setVerticalGroup(
            salida6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(salida11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel05, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(salida0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salida1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salida3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salida4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salida5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(7, 7, 7)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(salida11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salida10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salida9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salida8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salida7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salida6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelVehiculos.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 24)); // NOI18N
        jLabel1.setText("Veh??culos");

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 14)); // NOI18N
        jLabel7.setText("Camiones:");

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 14)); // NOI18N
        jLabel8.setText("Carros:");

        jSpinnerTruck.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jSpinnerTruck.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jSpinnerCar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jSpinnerCar.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        javax.swing.GroupLayout jPanelVehiculosLayout = new javax.swing.GroupLayout(jPanelVehiculos);
        jPanelVehiculos.setLayout(jPanelVehiculosLayout);
        jPanelVehiculosLayout.setHorizontalGroup(
            jPanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVehiculosLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jSpinnerCar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jSpinnerTruck, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelVehiculosLayout.setVerticalGroup(
            jPanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVehiculosLayout.createSequentialGroup()
                .addGroup(jPanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVehiculosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinnerCar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinnerTruck, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(414, Short.MAX_VALUE))
        );

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iaproyecto1/back-7.png"))); // NOI18N
        backButton.setEnabled(false);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });

        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iaproyecto1/next-7.png"))); // NOI18N
        nextButton.setEnabled(false);
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButtonMouseClicked(evt);
            }
        });

        jLabel6.setText("Paso inicial");

        jButtonAddData.setBackground(new java.awt.Color(0, 0, 204));
        jButtonAddData.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonAddData.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddData.setText("Colocar Elementos");
        jButtonAddData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddDataMouseClicked(evt);
            }
        });
        jButtonAddData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDataActionPerformed(evt);
            }
        });

        jLabel2.setText("Total de Pasos:");

        jLabel3.setText("Tiempo Tomado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelVehiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 509, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAddData)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelVehiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAddData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3))
                                .addComponent(backButton)
                                .addComponent(nextButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel00KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel00KeyPressed
        // TODO add your handling code here:
        System.out.println("Prueba");
    }//GEN-LAST:event_jPanel00KeyPressed

    private void jPanel00MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel00MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "0,0");
    }//GEN-LAST:event_jPanel00MouseClicked

    private void jPanel01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel01MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "0,1");
    }//GEN-LAST:event_jPanel01MouseClicked

    private void jPanel02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel02MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "0,2");
    }//GEN-LAST:event_jPanel02MouseClicked

    private void jPanel03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel03MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "0,3");
    }//GEN-LAST:event_jPanel03MouseClicked

    private void jPanel04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel04MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "0,4");
    }//GEN-LAST:event_jPanel04MouseClicked

    private void jPanel05MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel05MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "0,5");
    }//GEN-LAST:event_jPanel05MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "1,0");
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "1,1");
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "1,2");
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "1,3");
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "1,4");
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "1,5");
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "2,0");
    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "2,1");
    }//GEN-LAST:event_jPanel21MouseClicked

    private void jPanel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "2,2");
    }//GEN-LAST:event_jPanel22MouseClicked

    private void jPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "2,3");
    }//GEN-LAST:event_jPanel23MouseClicked

    private void jPanel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "2,4");
    }//GEN-LAST:event_jPanel24MouseClicked

    private void jPanel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "2,5");
    }//GEN-LAST:event_jPanel25MouseClicked

    private void jPanel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "3,0");
    }//GEN-LAST:event_jPanel30MouseClicked

    private void jPanel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "3,1");
    }//GEN-LAST:event_jPanel31MouseClicked

    private void jPanel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "3,2");
    }//GEN-LAST:event_jPanel32MouseClicked

    private void jPanel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel33MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "3,3");
    }//GEN-LAST:event_jPanel33MouseClicked

    private void jPanel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel34MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "3,4");
    }//GEN-LAST:event_jPanel34MouseClicked

    private void jPanel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel35MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "3,5");
    }//GEN-LAST:event_jPanel35MouseClicked

    private void jPanel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel40MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "4,0");
    }//GEN-LAST:event_jPanel40MouseClicked

    private void jPanel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel41MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "4,1");
    }//GEN-LAST:event_jPanel41MouseClicked

    private void jPanel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel42MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "4,2");
    }//GEN-LAST:event_jPanel42MouseClicked

    private void jPanel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel43MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "4,3");
    }//GEN-LAST:event_jPanel43MouseClicked

    private void jPanel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel44MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "4,4");
    }//GEN-LAST:event_jPanel44MouseClicked

    private void jPanel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel45MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "4,5");
    }//GEN-LAST:event_jPanel45MouseClicked

    private void jPanel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel50MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "5,0");
    }//GEN-LAST:event_jPanel50MouseClicked

    private void jPanel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel51MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "5,1");
    }//GEN-LAST:event_jPanel51MouseClicked

    private void jPanel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel52MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "5,2");
    }//GEN-LAST:event_jPanel52MouseClicked

    private void jPanel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel53MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "5,3");
    }//GEN-LAST:event_jPanel53MouseClicked

    private void jPanel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel54MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "5,4");
    }//GEN-LAST:event_jPanel54MouseClicked

    private void jPanel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel55MouseClicked
        // TODO add your handling code here:
        getPosicionPanel(evt, "5,5");
    }//GEN-LAST:event_jPanel55MouseClicked

    private void jButtonAddDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddDataMouseClicked
        // TODO add your handling code here:
        addDataManual();
    }//GEN-LAST:event_jButtonAddDataMouseClicked

    private void salida0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida0MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "0,4");
    }//GEN-LAST:event_salida0MouseClicked

    private void salida1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida1MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "1,4");
    }//GEN-LAST:event_salida1MouseClicked

    private void salida2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida2MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "2,4");
    }//GEN-LAST:event_salida2MouseClicked

    private void salida3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida3MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "3,4");
    }//GEN-LAST:event_salida3MouseClicked

    private void salida4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida4MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "4,4");
    }//GEN-LAST:event_salida4MouseClicked

    private void salida5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida5MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "5,4");
    }//GEN-LAST:event_salida5MouseClicked

    private void salida6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida6MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "4,0");
    }//GEN-LAST:event_salida6MouseClicked

    private void salida7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida7MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "4,1");
    }//GEN-LAST:event_salida7MouseClicked

    private void salida8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida8MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "4,2");
    }//GEN-LAST:event_salida8MouseClicked

    private void salida9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida9MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "4,3");
    }//GEN-LAST:event_salida9MouseClicked

    private void salida10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida10MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "4,4");
    }//GEN-LAST:event_salida10MouseClicked

    private void salida11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salida11MouseClicked
        // TODO add your handling code here:
        getPosicionSalida(evt, "4,5");
    }//GEN-LAST:event_salida11MouseClicked

    private void jButtonAddDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddDataActionPerformed

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        // TODO add your handling code here:
        pathIndex--;
        if (pathIndex<0){
            backButton.setEnabled(false);
            return;
        }
        else
            nextButton.setEnabled(true);
        printState(path.get(pathIndex));
        jLabel6.setText("Paso #" + pathIndex);
    }//GEN-LAST:event_backButtonMouseClicked

    private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
        // TODO add your handling code here:
        pathIndex++;
        if (pathIndex>path.size()-1){
            nextButton.setEnabled(false);
            return;
        }
        else 
            backButton.setEnabled(true);
        printState(path.get(pathIndex));
        jLabel6.setText("Paso: #" + pathIndex);
    }//GEN-LAST:event_nextButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIRush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIRush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIRush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIRush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIRush().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backButton;
    private javax.swing.JButton jButtonAddData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel00;
    private javax.swing.JPanel jPanel01;
    private javax.swing.JPanel jPanel02;
    private javax.swing.JPanel jPanel03;
    private javax.swing.JPanel jPanel04;
    private javax.swing.JPanel jPanel05;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanelVehiculos;
    private javax.swing.JSpinner jSpinnerCar;
    private javax.swing.JSpinner jSpinnerTruck;
    private javax.swing.JLabel nextButton;
    private javax.swing.JPanel salida0;
    private javax.swing.JPanel salida1;
    private javax.swing.JPanel salida10;
    private javax.swing.JPanel salida11;
    private javax.swing.JPanel salida2;
    private javax.swing.JPanel salida3;
    private javax.swing.JPanel salida4;
    private javax.swing.JPanel salida5;
    private javax.swing.JPanel salida6;
    private javax.swing.JPanel salida7;
    private javax.swing.JPanel salida8;
    private javax.swing.JPanel salida9;
    // End of variables declaration//GEN-END:variables

    public JPanel getLabel(String pos) {
        switch (pos) {
            case ("0,0"):
                return jPanel00;
            case ("0,1"):
                return jPanel01;
            case ("0,2"):
                return jPanel02;
            case ("0,3"):
                return jPanel03;
            case ("0,4"):
                return jPanel04;
            case ("0,5"):
                return jPanel05;

            case ("1,0"):
                return jPanel10;
            case ("1,1"):
                return jPanel11;
            case ("1,2"):
                return jPanel12;
            case ("1,3"):
                return jPanel13;
            case ("1,4"):
                return jPanel14;
            case ("1,5"):
                return jPanel15;

            case ("2,0"):
                return jPanel20;
            case ("2,1"):
                return jPanel21;
            case ("2,2"):
                return jPanel22;
            case ("2,3"):
                return jPanel23;
            case ("2,4"):
                return jPanel24;
            case ("2,5"):
                return jPanel25;

            case ("3,0"):
                return jPanel30;
            case ("3,1"):
                return jPanel31;
            case ("3,2"):
                return jPanel32;
            case ("3,3"):
                return jPanel33;
            case ("3,4"):
                return jPanel34;
            case ("3,5"):
                return jPanel35;

            case ("4,0"):
                return jPanel40;
            case ("4,1"):
                return jPanel41;
            case ("4,2"):
                return jPanel42;
            case ("4,3"):
                return jPanel43;
            case ("4,4"):
                return jPanel44;
            case ("4,5"):
                return jPanel45;

            case ("5,0"):
                return jPanel50;
            case ("5,1"):
                return jPanel51;
            case ("5,2"):
                return jPanel52;
            case ("5,3"):
                return jPanel53;
            case ("5,4"):
                return jPanel54;
            case ("5,5"):
                return jPanel55;
            default:
                return jPanel00;
        }
    }
}
