package info.androidhive.materialtabs.fragments;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iseeyou.util.BDSingleton;
import com.iseeyou.util.Respuesta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import info.androidhive.materialtabs.activity.IconTabsActivity;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import link.fls.swipestack.SwipeStack;
import link.fls.swipestacksample.MainActivity;
import link.fls.swipestacksample.NumberProgressBar;
import link.fls.swipestacksample.OnProgressBarListener;
import link.fls.swipestacksample.R;


public class OneFragment extends Fragment implements SwipeStack.SwipeStackListener, View.OnClickListener, OnProgressBarListener {
    private TextView mButtonLeft, alerta_mensaje, txtSwipeLeft, txtNombreEdadUbicacion;
    private RelativeLayout mButtonRight, button_icono_corazon, container_respuestas, contenedor_perfil_respuestas, icono_ayuda;
    private FloatingActionButton mFab;
    private Button buttonSwipeLeft2, buttonSwipeLeft3, buttonSwipeLeft4;
    private ArrayList<String> mData;
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;
    private ImageView imageView, botonIconoAyuda;
    private AnimatedVectorDrawable emptyHeart;
    private AnimatedVectorDrawable fillHeart;
    private boolean full = false;
    private View vistaPantalla;
    private Animation animShow, animHide, animmShowSlowUpRespuestas, animHidePerfilRespuestas, animShowPerfilRespuestas, animSlideRight;
    AlertDialog.Builder  alertDialogBuilder;
    private Timer timer, timer2, timer3;
    private NumberProgressBar bnp1;
    private NumberProgressBar bnp2;
    private NumberProgressBar bnp3;
    int progressBaxMax = 0;
    int progressBaxMax2 = 0;
    int progressBaxMax3 = 0;
    AlertDialog alertDialog;
    AlertDialog dialog;
    private int progress = 0;
    Button buttonEliminarRespuesta;
    private RingProgressBar progress_bar_1;
    private boolean esIncorrectaRsta;
    private boolean noTieneMasIntentos = false;
    private boolean estaActivoCorazon = false;
    public OneFragment() {
        // Required empty public constructor
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0) {
                if (progress < 100) {
                    progress++;
                    progress_bar_1.setProgress(progress);
                    progress_bar_1.setOnProgressListener(new RingProgressBar.OnProgressListener() {

                        @Override
                        public void progressToComplete() {
                            dialog.dismiss();
                            calcularEncuesta();
                        }
                    });
                }
            }
        }
    };

    private void calcularEncuesta() {
        //bnp.setReachedBarHeight(25);

        bnp1.setVisibility(View.VISIBLE);
        bnp2.setVisibility(View.VISIBLE);
        bnp3.setVisibility(View.VISIBLE);

        inicializarEncuesta();

//        botonIconoAyuda.setVisibility(View.GONE);
//        ImageView botoAyudaGris = (ImageView) findViewById(R.id.boton_ic_ayuda_gris);
//        botoAyudaGris.setVisibility(View.VISIBLE);

        bnp1.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(progressBaxMax < 72) {
                            bnp1.incrementProgressBy(1);
                            progressBaxMax = progressBaxMax + 1;
                        }

                    }
                });
            }
        }, 500, 50);

        bnp2.setOnProgressBarListener(this);
        timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(progressBaxMax2 < 21) {
                            bnp2.incrementProgressBy(1);
                            progressBaxMax2 = progressBaxMax2 + 1;
                        }
                    }
                });
            }
        }, 500, 50);

        bnp3.setOnProgressBarListener(this);
        timer3 = new Timer();
        timer3.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(progressBaxMax3 < 33) {
                            bnp3.incrementProgressBy(1);
                            progressBaxMax3 = progressBaxMax3 + 1;
                        }

                    }
                });
            }
        }, 500, 50);
    }

    public void inicializarEncuesta(){
        bnp1.setProgress(0);
        bnp2.setProgress(0);
        bnp3.setProgress(0);

        progressBaxMax = 0;
        progressBaxMax2 = 0;
        progressBaxMax3 = 0;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        mSwipeStack = (SwipeStack) rootView.findViewById(R.id.swipeStack);

        mButtonRight = (RelativeLayout) rootView.findViewById(R.id.buttonSwipeRight);
        button_icono_corazon = (RelativeLayout) rootView.findViewById(R.id.button_icono_corazon);
        mFab = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);



        txtSwipeLeft = (TextView) rootView.findViewById(R.id.txtSwipeLeft);
        // Botones de las 3 posibles respuestas
        buttonSwipeLeft2 = (Button) rootView.findViewById(R.id.buttonSwipeLeft2);
        buttonSwipeLeft3 = (Button) rootView.findViewById(R.id.buttonSwipeLeft3);
        buttonSwipeLeft4 = (Button) rootView.findViewById(R.id.buttonSwipeLeft4);

        buttonSwipeLeft2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                analizarRsta(v);
            }
        });

        buttonSwipeLeft3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                analizarRsta(v);
            }
        });

        buttonSwipeLeft4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                analizarRsta(v);
            }
        });
        txtNombreEdadUbicacion = (TextView) rootView.findViewById(R.id.txt_nombre_edad_ubicacion);
        txtNombreEdadUbicacion.setText(BDSingleton.getInstance().getPersonaActual().getNombreEdadUbicacionFormateada());

        //QQQ Se inicializa con la primer persona con su primer pregunta con el titulo
        txtSwipeLeft.setText((BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getTitulo()) + BDSingleton.getInstance().getPersonaActual().getTituloPosicion());

        //QQQ Se inicializa con la primer persona con su primer pregunta con las posibles respuestas
        buttonSwipeLeft2.setText(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(0))).getValor());
        buttonSwipeLeft2.setTag(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(0))).isEsCorrectaString());

        buttonSwipeLeft3.setText(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(1))).getValor());
        buttonSwipeLeft3.setTag(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(1))).isEsCorrectaString());

        buttonSwipeLeft4.setText(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(2))).getValor());
        buttonSwipeLeft4.setTag(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(2))).isEsCorrectaString());

        contenedor_perfil_respuestas = (RelativeLayout) rootView.findViewById(R.id.contenedor_perfil_respuestas);
        icono_ayuda = (RelativeLayout) rootView.findViewById(R.id.icono_ayuda);
        botonIconoAyuda = (ImageView) rootView.findViewById(R.id.boton_ic_ayuda);
        //mButtonLeft.setOnClickListener(this);
        mButtonRight.setOnClickListener(this);
        button_icono_corazon.setOnClickListener(this);
        mFab.setOnClickListener(this);
        //icono_ayuda.setOnClickListener(this);

        mData = new ArrayList<>();
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);

        fillWithTestData();

        imageView = (ImageView) rootView.findViewById(R.id.icono_corazon);
        emptyHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_empty);
        fillHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_fill);


        alerta_mensaje = (TextView) rootView.findViewById(R.id.txtMsgAvisoRsta);
        container_respuestas = (RelativeLayout) rootView.findViewById(R.id.container_respuestas);
        initAnimation();

        bnp1 = (NumberProgressBar)rootView.findViewById(R.id.numberbar1);
        bnp2 = (NumberProgressBar)rootView.findViewById(R.id.numberbar2);
        bnp3 = (NumberProgressBar)rootView.findViewById(R.id.numberbar3);        return rootView;
    }

    private void fillWithTestData() {
        for (int x = 0; x < 5; x++) {
            mData.add(getString(R.string.dummy_text) + " " + (x + 1));

        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mButtonRight)) {
            mSwipeStack.swipeTopViewToRight();
        } else if (v.equals(mFab)) {
            mData.add(getString(R.string.dummy_fab));
            mAdapter.notifyDataSetChanged();
        } else if (v.equals(button_icono_corazon)) {
            mSwipeStack.swipeTopViewToRight();
        } else if (v.equals(icono_ayuda)) {
            //calcularEncuesta();
            //quitarRespuesta();

        }
    }

    @Override
    public void onViewSwipedToLeft(int position) {

    }

    @Override
    public void onViewSwipedToRight(int position) {

    }

    @Override
    public void onStackEmpty() {

    }

    @Override
    public void onProgressChange(int current, int max) {

    }

    public class SwipeStackAdapter extends BaseAdapter {

        private List<String> mData;

        public SwipeStackAdapter(List<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private static final float BLUR_RADIUS = 25;

        public Bitmap blur(Bitmap image) {
            if (null == image) return null;

            Bitmap outputBitmap = Bitmap.createBitmap(image);
            final RenderScript renderScript = RenderScript.create(getActivity());
            Allocation tmpIn = Allocation.createFromBitmap(renderScript, image);
            Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

            //Intrinsic Gausian blur filter
            ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            theIntrinsic.setRadius(BLUR_RADIUS);
            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);
            return outputBitmap;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.card, parent, false);
            }

            ImageView fotoPersona = (ImageView) convertView.findViewById(R.id.imag_foto_persona);

            //QQQ Bajar la calidad de la imagen al nivel sin aciertos
            inicializarCalidadImagenAlNivelSinAciertos(fotoPersona, position);


            vistaPantalla = convertView;
            return convertView;
        }
    }

    private void inicializarCalidadImagenAlNivelSinAciertos(ImageView fotoPersona, int position) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), BDSingleton.getInstance().getPersonaPorPosition(position).getIdResourceImagen());
        float densidad = getResources().getDisplayMetrics().density;
        int width = (int) (bitmap.getWidth() * densidad);
        int height = (int) (bitmap.getHeight() * densidad);
        int widthFinal;
        int heightFinal;

        if(width > height){
            //QQQ Fijo 800 ancho
            double part1 = 800;
            double part2 = width;

            double division = part1 / part2;
            double resultado = division * height;
            widthFinal = 800;
            heightFinal = (int) resultado;
        } else{
            double part1 = 800;
            double part2 = height;
            double division = part1 / part2;

            double resultado = division * width;
            widthFinal = (int)  resultado;
            heightFinal = 800;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, 10, 10, true);
        bitmap = Bitmap.createScaledBitmap(bitmap, widthFinal, heightFinal, true);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

        fotoPersona.setImageBitmap(blur(decoded));

    }

    public Bitmap blur(Bitmap image) {
        if (null == image) return null;

        Bitmap outputBitmap = Bitmap.createBitmap(image);
        final RenderScript renderScript = RenderScript.create(getActivity());
        Allocation tmpIn = Allocation.createFromBitmap(renderScript, image);
        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

        //Intrinsic Gausian blur filter
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        theIntrinsic.setRadius(25);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    private void initAnimation()
    {
        animSlideRight  = AnimationUtils.loadAnimation( getActivity(), R.anim.slide_right);
        animSlideRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                buttonSwipeLeft4.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });
        animHide = AnimationUtils.loadAnimation( getActivity(), R.anim.view_hide);
        animHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {

                getActivity().runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        try{
                            alerta_mensaje.setVisibility(View.INVISIBLE);

                            buttonSwipeLeft2.setBackgroundResource(R.drawable.button_border_default);
                            buttonSwipeLeft3.setBackgroundResource(R.drawable.button_border_default);
                            buttonSwipeLeft4.setBackgroundResource(R.drawable.button_border_default);
                            buttonSwipeLeft2.setVisibility(View.VISIBLE);
                            buttonSwipeLeft3.setVisibility(View.VISIBLE);
                            buttonSwipeLeft4.setVisibility(View.VISIBLE);

                            //QQQ Se inicializa con la primer persona con su primer pregunta con el titulo
                            txtSwipeLeft.setText((BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getTitulo()) + BDSingleton.getInstance().getPersonaActual().getTituloPosicion());

                            buttonSwipeLeft2.setText(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(0))).getValor());
                            buttonSwipeLeft2.setTag(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(0))).isEsCorrectaString());

                            buttonSwipeLeft3.setText(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(1))).getValor());
                            buttonSwipeLeft3.setTag(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(1))).isEsCorrectaString());

                            buttonSwipeLeft4.setText(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(2))).getValor());
                            buttonSwipeLeft4.setTag(((Respuesta) (BDSingleton.getInstance().getPersonaActual().getPreguntaActual().getRespuestas().get(2))).isEsCorrectaString());

                            if(BDSingleton.getInstance().getPersonaActual().requiereBonusTrack()) {
                                openDialogPreguntaBonusTrack();
                            } else{
                                container_respuestas.startAnimation(animmShowSlowUpRespuestas);
                                //container_respuestas.setVisibility(View.INVISIBLE);
                            }
                        } catch (Exception e){

                        }

                    }

                });

            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });

        animHidePerfilRespuestas = AnimationUtils.loadAnimation( getActivity(), R.anim.view_hide_contenedor_perfil_respuestas);
        animHidePerfilRespuestas.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {

                getActivity().runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        try{
                            alerta_mensaje.setVisibility(View.INVISIBLE);
                            buttonSwipeLeft2.setBackgroundResource(R.drawable.button_border_default);
                            buttonSwipeLeft3.setBackgroundResource(R.drawable.button_border_default);
                            buttonSwipeLeft4.setBackgroundResource(R.drawable.button_border_default);
                            contenedor_perfil_respuestas.startAnimation( animmShowSlowUpRespuestas);

                        } catch (Exception e){

                        }

                    }

                });

            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });

        animmShowSlowUpRespuestas = AnimationUtils.loadAnimation( getActivity(), R.anim.view_show_up_respuestas);

        animShow = AnimationUtils.loadAnimation( getActivity(), R.anim.view_show);
        animShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if(BDSingleton.getInstance().getPersonaActual().tieneMasIntentos()){
                    mSwipeStack.acertoRespuesta();
                    if(BDSingleton.getInstance().getPersonaActual().getCountOk() == 2) {
                        estaActivoCorazon = true;
                        mostrarCorazonLlenoOVacio();
                    }else{
                        container_respuestas.startAnimation( animHide );
                    }
                } else {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            mSwipeStack.swipeTopViewToRight();
                        }
                    };
                    Handler handler = new Handler();
                    handler.postDelayed(runnable, 3500);

                }


            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });


        animShowPerfilRespuestas = AnimationUtils.loadAnimation( getActivity(), R.anim.view_show);
        animShowPerfilRespuestas.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                contenedor_perfil_respuestas.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });

    }

    public void mostrarCorazonLlenoOVacio() {
        AnimatedVectorDrawable drawable = full ? emptyHeart : fillHeart;
        imageView.setImageDrawable(drawable);
        drawable.start();
        full = !full;
    }

    private void openDialogPreguntaBonusTrack(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        //this is what I did to added the layout to the alert dialog
        View layout=inflater.inflate(R.layout.custom_dialog_bonus_track,null);
        alert.setView(layout);
        alert.setPositiveButton("Si, dale!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                esIncorrectaRsta = false;
                container_respuestas.startAnimation(animmShowSlowUpRespuestas);
            } });


        alert.setNegativeButton("No, gracias.", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mSwipeStack.swipeTopViewToRight();
            } });
        alert.setTitle("Tenes 600 creditos disponibles");
        dialog = alert.create();

        dialog.show();

    }

    public void analizarRsta(View view) {
        // QQQ Borra la encuesta en caso de que estuvierA seteada
        borrarEncuestaRealizada();

        //QQQ Queda seleccionado en gris la respuesta que apreto el usuario
        view.setBackgroundResource(R.drawable.button_border_focused);

        //QQQ Avanzar la cantidad a la siguiente pregunta
        BDSingleton.getInstance().getPersonaActual().avanzarPregunta();

        String tag = (String) view.getTag();
        alerta_mensaje.setVisibility(View.VISIBLE);
        if("1".equals(tag)) {
            //QQQ RESPUESTA CORRECTA
            alerta_mensaje.setBackgroundColor(Color.parseColor("#14E350"));
            BDSingleton.getInstance().getPersonaActual().sumarAcierto();

            if (BDSingleton.getInstance().getPersonaActual().getCountOk() == 2) {
                txtSwipeLeft.setVisibility(View.INVISIBLE);
                buttonSwipeLeft2.setVisibility(View.INVISIBLE);
                buttonSwipeLeft3.setVisibility(View.INVISIBLE);
                buttonSwipeLeft4.setVisibility(View.INVISIBLE);
                alerta_mensaje.setBackgroundColor(Color.parseColor("#FF731D"));
                alerta_mensaje.setText("Sos 100% compatible, hace click al corazon para conocerla!");
            } else {
                alerta_mensaje.setText("Excelente respuesta!, te falta un acierto mas.");
            }
        }

        if("0".equals(tag)) {
            //QQQ RESPUESTA INCORRECTA
            BDSingleton.getInstance().getPersonaActual().sumarError();

            alerta_mensaje.setBackgroundColor(Color.parseColor("#E55451"));
            alerta_mensaje.setText("Ups!, no era esa la respuesta. Suerte en la proxima!");
            esIncorrectaRsta = true;
        }
/*
        if("3".equals(tag)) {
            //QQQ RESPUESTA INCORRECTA
            txtSwipeLeft.setVisibility(View.INVISIBLE);
            buttonSwipeLeft2.setVisibility(View.INVISIBLE);
            buttonSwipeLeft3.setVisibility(View.INVISIBLE);
            buttonSwipeLeft4.setVisibility(View.INVISIBLE);
            alerta_mensaje.setBackgroundColor(Color.parseColor("#E55451"));
            alerta_mensaje.setText("Ups!, no era esa la respuesta. Se acabaron los 3 intentos");
            noTieneMasIntentos = true;
        }

*/

        alerta_mensaje.startAnimation( animShow );
    }

    private void borrarEncuestaRealizada() {
        bnp1.setVisibility(View.GONE);
        bnp2.setVisibility(View.GONE);
        bnp3.setVisibility(View.GONE);

//        botonIconoAyuda.setVisibility(View.VISIBLE);
//        ImageView botoAyudaGris = (ImageView) findViewById(R.id.boton_ic_ayuda_gris);
//        botoAyudaGris.setVisibility(View.GONE);
    }

}
