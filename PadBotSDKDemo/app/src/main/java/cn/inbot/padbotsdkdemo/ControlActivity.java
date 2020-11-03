package cn.inbot.padbotsdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import cn.inbot.padbotsdk.Robot;
import cn.inbot.padbotsdk.RobotManager;
import cn.inbot.padbotsdk.constant.RobotDisconnectType;
import cn.inbot.padbotsdk.listener.RobotConnectionListener;
import cn.inbot.padbotsdk.listener.RobotListener;
import cn.inbot.padbotsdk.model.ObstacleDistanceData;

public class  ControlActivity extends AppCompatActivity implements RobotConnectionListener,RobotListener {

    private Robot robot;
    private String serialNumber;
    private int model;

    private TextView nameValueTv;
    private TextView connectStatusValueTv;
    private TextView obstacleValueTv;
    private TextView batteryValueTv;
    private TextView hardwareVersionTv;
    private TextView soundSourceTv;

    private EditText distanceEt;
    private EditText angleEt;

    private EditText executeTextInputEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        setTitle("Control Robot");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RobotManager.getInstance(getApplication()).setRobotConnectionListener(this);
        RobotManager.getInstance(getApplication()).openSoundSourceAngleListener();

        Intent intent = getIntent();
        model = intent.getIntExtra("model", 0);
        serialNumber = intent.getStringExtra("serialNumber");

        connectStatusValueTv = (TextView) findViewById(R.id.control_connect_status_value_tv);
        nameValueTv = (TextView) findViewById(R.id.control_name_value_tv);
        nameValueTv.setText(serialNumber);


        obstacleValueTv = (TextView) findViewById(R.id.control_obstacle_tv);
        batteryValueTv = (TextView) findViewById(R.id.control_battery_tv);
        hardwareVersionTv = (TextView) findViewById(R.id.control_hardware_version_tv);
        soundSourceTv = (TextView) findViewById(R.id.control_sound_source_tv);

//        distanceEt = (EditText) findViewById(R.id.control_move_distance_et);
        angleEt = (EditText) findViewById(R.id.control_turn_angle_et);

        //Task 2
        executeTextInputEt = (EditText) findViewById(R.id.task2_text_input);

        //Task 3
        //Intent intent = getIntent(); already defined earlier
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            if (null != robot) {
                int speed = Integer.parseInt(sharedText);
                robot.goForward(speed);
            }
        }
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Control the robot
     * @param view
     */
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.control_connect_bt:

                connectStatusValueTv.setText("Connecting...");

                if (1 == model) {
                    RobotManager.getInstance(getApplication()).connectRobotByBluetooth(serialNumber);
                }
                else if (2 == model) {
                    RobotManager.getInstance(getApplication()).connectRobotBySerialPort();
                }

                break;

            case R.id.control_disconnect_bt:

                connectStatusValueTv.setText("Disconnecting...");

                RobotManager.getInstance(getApplication()).disconnectRobot();

                break;

            case R.id.control_stop_bt:

                if (null != robot) {
                    robot.stop();
                }
                break;

            case R.id.control_forward_bt:

                if (null != robot) {
                    robot.goForward();
                }

                break;

            case R.id.control_back_bt:

                if (null != robot) {
                    robot.goBackward();
                }

                break;

            case R.id.control_left_bt:

                if (null != robot) {
                    robot.turnLeft();
                }

                break;

            case R.id.control_right_bt:

                if (null != robot) {
                    robot.turnRight();
                }

                break;

            case R.id.control_left_front_bt:

                if (null != robot) {
                    robot.goForwardLeft(4);
                }

                break;

            case R.id.control_right_front_bt:

                if (null != robot) {
                    robot.goForwardRight(4);
                }

                break;

            case R.id.control_left_back_bt:

                if (null != robot) {
                    robot.goBackwardLeft(4);
                }

                break;

            case R.id.control_right_back_bt:

                if (null != robot) {
                    robot.goBackwardRight(4);
                }

                break;

            case R.id.control_head_rise_bt:

                if (null != robot) {
                    robot.headRise();
                }

                break;

            case R.id.control_head_down_bt:

                if (null != robot) {
                    robot.headDown();
                }

                break;

            case R.id.control_go_charging_bt:

                if (null != robot) {
                    robot.goCharging();
                }

                break;

            case R.id.control_stop_charging_bt:

                if (null != robot) {
                    robot.stopCharging();
                }

                break;

            /*
            case R.id.control_obstacle_on_bt:

                if (null != robot) {
                    robot.turnOnObstacleDetection();
                }

                break;
            */
            case R.id.control_obstacle_off_bt:

                if (null != robot) {
                    robot.turnOffObstacleDetection();
                }

                break;

            case R.id.control_1st_speed_bt:

                if (null != robot) {
                    robot.setMovementSpeed(1);
                }

                break;

            case R.id.control_3rd_speed_bt:

                if (null != robot) {
                    robot.setMovementSpeed(3);
                }

                break;

            case R.id.control_obstacle_bt:

                if (null != robot) {
                    robot.queryObstacleDistanceData();
                }

                break;

            case R.id.control_battery_bt:

                if (null != robot) {
                    robot.queryBatteryPercentage();
                }

                break;

            case R.id.control_hardware_version_bt:

                if (null != robot) {
                    robot.queryRobotHardwareVersion();
                }

                break;
//
//            case R.id.control_go_forward_with_arg_bt:{
//
//                String distanceStr = distanceEt.getText().toString();
//                int distance = 0;
//                try {
//                    distance = Integer.parseInt(distanceStr);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "Please enter a positive integer", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (null != robot) {
//                    robot.goForward(distance);
//                }
//
//                break;
//            }

            case R.id.control_turn_left_with_arg_bt:{
                String angleStr = angleEt.getText().toString();
                int angle = 0;
                try {
                    angle = Integer.parseInt(angleStr);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Please enter a positive integer", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (null != robot) {
                    robot.turnLeft(angle);
                }

                break;
            }
            case R.id.control_turn_right_with_arg_bt:{
                String angleStr = angleEt.getText().toString();
                int angle = 0;
                try {
                    angle = Integer.parseInt(angleStr);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Please enter a positive integer", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (null != robot) {
                    robot.turnRight(angle);
                }

                break;
            }

            case R.id.control_sound_source_bt: {

                if (null != robot) {
                    robot.querySoundSourceAngle();
                }

                break;
            }

            //TASK 1 
            case R.id.star_path: {

                if (null != robot) {
                    try {
                        for (int i = 0; i < 6; i++) {
                            synchronized (this) {
                                robot.goBackward(30);
                            }
                            Thread.sleep(4000);

                            synchronized (this) {
                                robot.turnRight(324);
                            }
                            Thread.sleep(4000);
                        }
                    } catch (Exception e) { }
                }
                break;
            }

            case R.id.happy_path: {
                if (null != robot) {
                    synchronized (this) {
                        try {
                            robot.turnRight();
                            Thread.sleep(5200);
                            robot.stop();
                        } catch (Exception e) {}
                    }

                }
                break;
            }

            case R.id.sad_path: {
                if (null != robot) {
                    synchronized (this) {
                        try {
                            robot.turnRight(45);
                            Thread.sleep(2000);
                            robot.turnLeft(90);
                            Thread.sleep(3800);
                            robot.turnRight(45);
                            Thread.sleep(2000);
                            robot.stop();
                        } catch (Exception e) {}
                    }
                }
                break;
            }

            //TASK 2
            case R.id.execute_text_input:{
                String executeText = executeTextInputEt.getText().toString();

                //Split string into a list
                List<String> items = Arrays.asList(executeText.split("[\\s-]+"));

                try {
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Text input", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (null != robot) {
                    try {
                        for (String s : items) {
                            synchronized (this) {
                                int speed = 0;
                                String[] part = s.split("(?<=\\D)(?=\\d)");
                                String dir = part[0];
                                speed = Integer.parseInt(part[1]);

                                if (dir.equalsIgnoreCase("F")) //Go Forward
                                    robot.goForward(speed);

                                if (dir.equalsIgnoreCase("B")) //Go Backward
                                    robot.goBackward(speed);

                                if (dir.equalsIgnoreCase("R")) //Turn Right
                                    robot.turnRight(speed);

                                if (dir.equalsIgnoreCase("L")) //Turn Left
                                    robot.turnLeft(speed);

                                Thread.sleep(3000);
                            } //End synchronized
                        }
                    } catch (Exception e) { }
                }
            }
            default:
                break;

        }

    }

    /**
     * invoke when connect the robot successfully
     * @param robot The connected robot instance.
     */
    @Override
    public void onRobotConnected(final Robot robot) {

        this.robot = robot;
        this.robot.setListener(this);

        nameValueTv.setText(robot.getRobotSerialNumber());
        connectStatusValueTv.setText("Connected");
    }

    /**
     * invoken when connect the robot unsuccessfully
     * @param robotSerialNumber The serial number of the robot.
     */
    @Override
    public void onRobotConnectFailed(final String robotSerialNumber) {

        this.robot = null;

        connectStatusValueTv.setText("Connect failed");
    }

    /**
     * Invoke when the robot disconnected
     * @param robotSerialNumber The serial number of the robot.
     * @param disconnectedType  disconnect type.
     */
    @Override
    public void onRobotDisconnected(String robotSerialNumber, RobotDisconnectType disconnectedType) {

        this.robot = null;

        connectStatusValueTv.setText("Disconnected");
    }


    @Override
    public void onReceivedRobotObstacleDistanceData(final ObstacleDistanceData obstacleDistanceData) {

        obstacleValueTv.setText("result:" + obstacleDistanceData.getFirstDistance() + ","
                + obstacleDistanceData.getSecondDistance() + ","
                + obstacleDistanceData.getThirdDistance() + ","
                + obstacleDistanceData.getFourthDistance() + ","
                + obstacleDistanceData.getFifthDistance());
    }

    @Override
    public void onReceivedRobotBatteryPercentage(final int batteryPercentage) {
        batteryValueTv.setText("result:" + batteryPercentage);
    }

    @Override
    public void onReceivedRobotHardwareVersion(final int version) {
        hardwareVersionTv.setText("result:" + version);
    }

    @Override
    public void onReceivedRobotSerialNumber(String serialNumber) {
        nameValueTv.setText(serialNumber);
    }

    @Override
    public void onReceivedCustomData(String data) {

    }

    @Override
    public void onReceivedSoundSourceAngle(int angle) {

        soundSourceTv.setText("" + angle);

    }
}
