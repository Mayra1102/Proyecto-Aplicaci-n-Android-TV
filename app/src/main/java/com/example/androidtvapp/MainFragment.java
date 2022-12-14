package com.example.androidtvapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.leanback.app.BrowseFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;

import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;

import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends BrowseFragment {


    private static final String TAG = MainFragment.class.getSimpleName();

    private static final int GRID_ITEM_WIDTH = 300;
    private static final int GRID_ITEM_HEIGHT = 200;

    @SuppressLint("StaticFieldLeak")
    private static PicassoBackgroundManager picassoBackgroundManager = null;
    String description = "";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        setupUIElements();

        loadRows();

        setupEventListeners();

        picassoBackgroundManager = new PicassoBackgroundManager(getActivity());
    }

    private void setupEventListeners() {
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
        setOnItemViewClickedListener(new ItemViewClickedListener());
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            // each time the item is clicked, code inside here will be executed.
            if (item instanceof Movie) {
                Movie movie = (Movie) item;
                Log.d(TAG, "Item: " + item.toString());
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.MOVIE, movie);

                getActivity().startActivity(intent);
            } else if (item instanceof String) {
                if (item == "ErrorFragment") {
                    Intent intent = new Intent(getActivity(), ErrorActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    private static final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
            // each time the item is selected, code inside here will be executed.
            if (item instanceof String) {
                // GridItemPresenter
                // imagen de fondo principal (bad bunny)
                picassoBackgroundManager.updateBackgroundWithDelay("https://i.pinimg.com/736x/db/ea/bf/dbeabf79035bdd339d9dbd017ec3e3b2.jpg");
            } else if (item instanceof Movie) {
                // CardPresenter
                picassoBackgroundManager.updateBackgroundWithDelay(((Movie) item).getCardImageUrl());
            }
        }
    }

    private void setupUIElements() {
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.lb_default_search_color));
        // set search icon colorlb_default_brand_color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void loadRows() {
        ArrayObjectAdapter mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        /* GridItemPresenter */
       // titulo principal
        HeaderItem gridItemPresenterHeader = new HeaderItem(0, "Bad Bunny");

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        // recuadro por encima de los sencillos
        gridRowAdapter.add("Oasis");
        mRowsAdapter.add(new ListRow(gridItemPresenterHeader, gridRowAdapter));

        /* CardPresenter */
        //subcategoria de titulo principal
        HeaderItem cardPresenterHeader = new HeaderItem(1, "Urbano Latino");
        CardPresenter cardPresenter = new CardPresenter();
        ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(cardPresenter);

        for (int i = 0; i < 5; i++) {
            Movie movie = new Movie();

            if (i == 0) {
                // portada de "la cancion"
                movie.setCardImageUrl("https://sss.moda.pe/imagen/apaisado/j-balvin-saludo-a-bad-bunny-por-su-cumpleanos-con-divertidas-fotografias2-a84f8.jpg");
                movie.setTitle("La canci??n");
                movie.setStudio("Oasis");
                description = "Yeh, yeh, yeh\n" +
                        "Pensaba que te hab??a olvidao, eh\n" +
                        "Pero pusieron la canci??n, eh, eh, eh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Pensaba que te hab??a olvidao, eh\n" +
                        "Pero pusieron la canci??n, eh, eh, eh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Pensaba que te hab??a olvidao\n" +
                        "Justo cuando cre??a\n" +
                        "Que por comerme a dos o diez, te olvidar??a (yeh)\n" +
                        "Cog?? un respiro y me sal?? de la v??a\n" +
                        "Y como un pendejo, no sab??a lo que hac??a\n" +
                        "Nunca lo super?? (no), nunca te super?? (no)\n" +
                        "Hasta me aprend?? to'a las baladas en ingl??s (yeh)\n" +
                        "Respir?? y cont?? hasta tres\n" +
                        "Eres la fantas??a oscura de Kanye West, beb??, ey\n" +
                        "Hace tiempo lo barato me sali?? caro (yih)\n" +
                        "Yo s??lo twitteo, balas locas disparo (yih)\n" +
                        "??C??mo olvidar la bellaquera en el carro?\n" +
                        "??A que gui?? solo?\n" +
                        "Pensaba que te hab??a olvidao (pero no), yeh\n" +
                        "Pero pusieron la canci??n, yeh, yeh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Pensaba que te hab??a olvidao, eh\n" +
                        "Pero pusieron la canci??n, yeh, yeh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos, ey, ey\n" +
                        "Y hace tiempo que no ven??as a mi cabeza\n" +
                        "Pero ya van par de cervezas\n" +
                        "Y me acord?? de c??mo t?? me besas\n" +
                        "De to' los polvos encima 'e la mesa\n" +
                        "Y en el carro, la playa, y el motel\n" +
                        "En casa de tu pai, cuando yo te iba a ver\n" +
                        "Las veces que tu mai nos lleg?? a coger\n" +
                        "T?? brincando moja??ta, sudando Chanel\n" +
                        "Yo s?? que lo nuestro es cosa de ayer\n" +
                        "Y me pone contento que te va bien con ??l\n" +
                        "Yo ni te extra??aba ni te quer??a ver\n" +
                        "Pero pusieron la canci??n que te gustaba poner\n" +
                        "Y me acord?? de ti, cuando me hiciste feliz\n" +
                        "Se acab??, pues, me fui, ey\n" +
                        "Yo mismo me r??o de m?? porque\n" +
                        "Pensaba que te hab??a olvidao, eh\n" +
                        "Pero pusieron la canci??n, yeh, yeh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Y yo pensaba que tu nombre estaba muerto, eh\n" +
                        "Pero te so???? despierto, ey\n" +
                        "Hoy sal?? pa' la calle suelto\n" +
                        "Sin sentimientos, el coraz??n desierto\n" +
                        "Y yo pensaba que tu nombre estaba muerto\n" +
                        "Pero te so???? despierto\n" +
                        "Hoy sal?? pa' la calle suelto\n" +
                        "Sin sentimientos, el coraz??n desierto";

            } else if (i == 1) {
                movie.setCardImageUrl("https://e.rpp-noticias.io/normal/2019/08/23/832044j-balvin-bad-bunny-cuidao-por-ahjpg.jpg");
                movie.setTitle("Cuidao por ah??");
                movie.setStudio("Oasis");
                description = "Leggo\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale\n" +
                        "Baby, cuida'o por ah?? (por ah??)\n" +
                        "Que eso se te sale, que eso se te sale\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale\n" +
                        "Baby, cuida'o por ah?? (cuida'o)\n" +
                        "Cuando el nene sale (ey, ey)\n" +
                        "El pantisito hecho un aro y se lo puso\n" +
                        "Baby, aqu?? me tienes, dame uso (ey)\n" +
                        "Eso ah?? atr??' no' tiene confuso'\n" +
                        "Mami, deja ya el abuso, eh, eh\n" +
                        "Explota como bencina\n" +
                        "Cachorrita solo fuma Purina\n" +
                        "No s?? es de Cali o de Carolina\n" +
                        "Yo quiero ser tu Daddy, pa' darte Gasolina\n" +
                        "El culo grande y el traje chiquito\n" +
                        "Ese queso yo te lo derrito\n" +
                        "Shh, yo me quedo callaito\n" +
                        "(Dale papi, que yo no me quito), ey\n" +
                        "Ni yo sin mover el burrito\n" +
                        "Ten cuida'o que hay mucho morrito\n" +
                        "Me lo pide sin gorrito\n" +
                        "(Dale papi, que yo no me quito)\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti (ey)\n" +
                        "Dale mami, voy a ti (ey)\n" +
                        "Dale voy a ti\n" +
                        "Dale mami, voy a ti (ey)\n" +
                        "Dale mami, voy a ti (??Qu?? fue?)\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale\n" +
                        "Baby, cuida'o por ah?? (por ah??)\n" +
                        "Que eso se te sale, que eso se te sale\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale (rrr, ey)\n" +
                        "Baby, cuida'o por ah??\n" +
                        "Cuando el nene sale (ey, ey)\n" +
                        "Okay, vamo' p'al verso\n" +
                        "T?? tiene' un flow demasiado 'e perverso\n" +
                        "Y yo tengo un reggaeton demasia'o extenso\n" +
                        "To' los que te tiran s?? que van pal friendzone (ey)\n" +
                        "Diabla, ??qu?? es lo que t?? habla'? (habla')\n" +
                        "T?? sabe' bien que te quiero dar tabla (tabla, tabla)\n" +
                        "Perro que muerde casi nunca ladra (rrr)\n" +
                        "Quiere hacer maldade' como una pobre diabla (ey)\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale voy a ti\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti (??Qu?? fue?, Ey, ey)\n" +
                        "Vamo' a hacer maldade' (ey, ey)\n" +
                        "No le pare y dale (ey, ey)\n" +
                        "Baby, cuida'o por ah?? (por ah??)\n" +
                        "Que eso se te sale, que eso se te sale\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale (rrr, ey)\n" +
                        "Baby, cuida'o por ah?? (cuida'o)\n" +
                        "Cuando el nene sale (ey, ey)\n" +
                        "Yo\n" +
                        "Bad Bunny, baby\n" +
                        "J Balvin, man\n" +
                        "Bad Bunny, baby\n" +
                        "Bad Bunny\n" +
                        "J Balvin, man\n" +
                        "Oasis\n" +
                        "Tainy\n" +
                        "Esto es pa' ti beb??\n" +
                        "Mu??velo, mu??velo\n" +
                        "Mu??velo, mu??velo\n" +
                        "Mu??velo, mu??velo\n" +
                        "Mu??velo, mu??velo\n" +
                        "Latino Gang\n" +
                        "Mu??velo, mu??velo\n" +
                        "Mu??velo, mu??velo\n" +
                        "Bad Bunny, J Balvin, man, Tainy\n" +
                        "Mu??velo, ey\n" +
                        "(Woo)\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti (ey)\n" +
                        "Dale voy a ti\n" +
                        "Yo s?? que puedes, dale, voy a ti (eh eh)\n" +
                        "Dale mami, voy a ti\n" +
                        "Jaja, ey";

            } else if (i == 2) {
                movie.setCardImageUrl("https://www.informador.mx/__export/1562187820184/sites/elinformador/img/2019/07/03/balvin_crop1562187749749.jpg_1902800913.jpg");
                movie.setTitle("Odio");
                movie.setStudio("Oasis");
                description = "No quiero saber de ti (No)\n" +
                        "No s?? qu?? haces aqu??\n" +
                        "Ya yo te lo advert?? (Yeh, yeh)\n" +
                        "As?? que, mami, no te me ilusiones\n" +
                        "Que pa' ti ya yo no escribo canciones\n" +
                        "Lo que digan, me tiene sin cojones\n" +
                        "Mala m??a, beb??, no te encojones\n" +
                        "Pero, en realidad, a ti ya yo te odio, odio (Huh)\n" +
                        "Pase de amor al odio (Odio)\n" +
                        "Que me perdone Dios, pero te odio (??Eh!)\n" +
                        "??Que no quiero na' contigo? Eso es obvio (No, no, no, no, no)\n" +
                        "Si quieres, te lo meto, pero ser?? con odio, odio\n" +
                        "Pase de amor al odio (Odio)\n" +
                        "Que me perdone Dios, pero te odio (??Eh!)\n" +
                        "??Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero ser?? con odio\n" +
                        "Tengo par de v??deos sexuales contigo por ah?? (S??)\n" +
                        "T?? no te merece' a nadie el d??a 'e San Valent??n (No, no)\n" +
                        "Pa'l carajo con tu novio, quiero que no sea' feliz\n" +
                        "Y en la calle se comente lo duro que yo te di (Wuh, wuh, wuh)\n" +
                        "La calle y el trabajo (huh), y los hoteles caros (Okay)\n" +
                        "Si yo sab??a cu??nto ganabas, se me hizo raro (All right, all right)\n" +
                        "En un yate en Miami, al otro d??a en Dubai (Wuh)\n" +
                        "No me creas tan pendejo, ya eso e' lo que hay (Hay)\n" +
                        "Rencor y remordimiento, s??\n" +
                        "Lo tuyo y lo m??o se fue con el viento, ey\n" +
                        "Ando por ah?? con otra, en tu asiento (S??)\n" +
                        "Ayer fuimos a comer y me gast?? 500 (Ching!)\n" +
                        "Y es por el maldito odio que ahora te tengo (Yeh)\n" +
                        "S??, voy a hacerte un velorio y de ti me vengo\n" +
                        "Tranquila, de noche, t?? vas a volver (Aj??)\n" +
                        "Pidiendo que te lo haga solamente por placer y lo voy a hacer\n" +
                        "Pero para que entiendas (Okay?)\n" +
                        "Que si tienes tienda es pa' que la atiendas\n" +
                        "Te confiaste y te cre??ste la jodienda (La jodienda)\n" +
                        "Pa' ti ya no tengo espacio en mi agenda, baby\n" +
                        "No te sorprendas (Wuh, wuh)\n" +
                        "Cre?? que esto val??a m??s que to'a mis prendas\n" +
                        "Te confiaste y te cre??ste la jodienda\n" +
                        "Y ahora quisiera perdonarte\n" +
                        "Pero, en realidad, a ti ya yo te odio, odio (??C??mo?)\n" +
                        "Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio\n" +
                        "??Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero ser?? con odio, odio\n" +
                        "Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio\n" +
                        "??Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto (Pero ser?? con odio)\n" +
                        "Te deseo lo peor (Eh), que tengas mala suerte\n" +
                        "Que sufras en la vida y hasta despu??s de la muerte (Eh)\n" +
                        "Borre tus foto', ya no quiero verte (??No!)\n" +
                        "Lo mejor que me ha pasado fue perderte (Wu huh)\n" +
                        "Y no, no, no me busques m??', no me busques m??', que no estoy pa' ti\n" +
                        "No, no, no me busques m??', no me busques m??'\n" +
                        "Y si te trato mal (Eh), no e' intencional (No)\n" +
                        "Es que por m??s que t?? quieras, no te puedo amar (Na')\n" +
                        "Si te trato mal (Eh), no e' intencional\n" +
                        "Es que por m??' que t?? quiera' arreglar\n" +
                        "Ya yo te odio, odio (Eh)\n" +
                        "Pase de amor al odio (Odio)\n" +
                        "Que me perdone Dios, pero te odio (Wuh)\n" +
                        "??Que no quiero na' contigo? Eso es obvio (Yeh, yeh, yeh, yeh, yeh, yeh)\n" +
                        "Si quieres, te lo meto, pero ser?? con odio, odio\n" +
                        "Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio\n" +
                        "??Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero ser?? con odio, oh (Na')\n" +
                        "(Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio)\n" +
                        "(??Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero ser?? con odio, oh)\n" +
                        "(Y es por el maldito odio que ahora te tengo)";
            } else if (i == 3) {
                movie.setCardImageUrl("https://rollingstoneindia.com/wp-content/uploads/2019/06/Screen-Shot-2019-06-28-at-2.55.11-PM.png");
                movie.setTitle("Qu?? pretendes");
                movie.setStudio("Oasis");
                description = "Uah\n" +
                        "Uah, uah\n" +
                        "Yeah, yeah, yeah, yeah (Uh-uh)\n" +
                        "Ahora quiere' volver (Ahora quieres volver, yeah)\n" +
                        "??Por qu?? raz??n? Dime para qu?? (??Para qu??, para qu???)\n" +
                        "Ya no te presto atenci??n (Ninguna)\n" +
                        "Desde hace tiempo le puse punto final (S??)\n" +
                        "??Qu?? pretendes t?? llam??ndome a esta hora? (Yeah, hora)\n" +
                        "Esa actitud la conozco ya (Yeah-yeah-yeah)\n" +
                        "Sabes qu?? hacer muy bien para envolverme (Envolverme)\n" +
                        "Pero esta vez es muy tarde ya (Uah, oh-oh-oh)\n" +
                        "Estas no son horas de llamar (No)\n" +
                        "Al meno' que me lo quiera' mamar\n" +
                        "Que quiera' prender, que quiera' quemar (Uh-huh)\n" +
                        "Hablando claro, ya t?? me cae' hasta mal (Ja)\n" +
                        "Por ti me met?? pastilla' y me fui de overflow Lamar (Woh)\n" +
                        "Pero t?? no ere' una Kardashian (No)\n" +
                        "Contigo no me tiro, porque si no las retro se me embachan\n" +
                        "De Snapchat te borr??, de Facebook te borr??\n" +
                        "De Instagram te borr??, de mi vida te borr?? (??Plo!)\n" +
                        "Y ahora quiere' volver (??Nah!)\n" +
                        "Nah, t?? lo que quiere' e' joder (??Wuh!)\n" +
                        "Pero no se va a poder (Fuck you)\n" +
                        "Me va' a ver con otra y te va' a morder (Uh)\n" +
                        "Y ahora quiere' volver\n" +
                        "Nah, t?? lo que quiere' e' joder\n" +
                        "Pero no se va a poder\n" +
                        "Me vas a ver con otra y te va' a morder (??Nah!)\n" +
                        "??Qu?? pretendes t?? llam??ndome a esta hora? (Ey)\n" +
                        "Esa actitud la conozco ya\n" +
                        "Sabes qu?? hacer muy bien para envolverme\n" +
                        "Pero esta vez es muy tarde ya\n" +
                        "Yeah-yeah-yeah-yeah-yeah-yeah\n" +
                        "Intentas hacerlo todo para que yo vuelva\n" +
                        "Las cosas no son iguales, ??Para qu?? insistir?\n" +
                        "Evita molestias y tu tiempo no pierdas\n" +
                        "Conmigo no encuentras nada\n" +
                        "A escondidas vives chequeando las fotos\n" +
                        "Investigando mi perfil\n" +
                        "No lo niegues, bien te conozco\n" +
                        "Todo lo que t?? hiciste conmigo\n" +
                        "Quiere' repetirlo\n" +
                        "Anda buscando m??s\n" +
                        "Y a m?? eso me da igual\n" +
                        "Todo lo que t?? hiciste conmigo\n" +
                        "Quiere' repetirlo\n" +
                        "Andas buscando m??s\n" +
                        "Y a m?? eso me da igual\n" +
                        "??Qu?? pretendes t?? llam??ndome a esta hora?\n" +
                        "Esa actitud la conozco ya (Yeah-yeah-yeah)\n" +
                        "Sabes qu?? hacer muy bien para envolverme (Envolverme)\n" +
                        "Pero esta vez es muy tarde ya (Uah, oh-oh-oh)\n" +
                        "??Qu?? pretendes t?? llam??ndome a esta hora? (Ey)\n" +
                        "Esa actitud la conozco ya\n" +
                        "Sabes qu?? hacer muy bien para envolverme\n" +
                        "Pero esta vez es muy tarde ya\n" +
                        "Yeah-yeah-yeah-yeah-yeah-yeah\n" +
                        "Sky Rompiendo\n" +
                        "Yeah, yeah, yeah, yeah\n" +
                        "Rompiendo el bajo\n" +
                        "Bad Bunny, baby-be-be-beb??-beb??, beb??\n" +
                        "J Balvin\n" +
                        "Leggo'\n" +
                        "J Balvin\n" +
                        "Bad Bunny\n" +
                        "Oasis\n" +
                        "Oasis, baby\n" +
                        "Esta va pa' ti";
            } else {
                movie.setCardImageUrl("https://cloudfront-us-east-1.images.arcpublishing.com/gruponacion/FHAZ5XJRZFA7HFIZIHAATDZBSU.png");
                movie.setTitle("Como un beb??");
                movie.setStudio("Oasis");
                description = "Legendary Beatz\n" +
                        "Yeah, Oasis\n" +
                        "Leggo\n" +
                        "Leggo\n" +
                        "Trato, trato y queda nada\n" +
                        "Peleamos otra vez (otra vez, otra vez, otra vez, otra vez)\n" +
                        "Trato, trato a veces me habla\n" +
                        "Y a veces no tambi??n (??Por qu???)\n" +
                        "Como un beb??\n" +
                        "Mami ya, mami ya (ya)\n" +
                        "Me cans?? de pelear (no)\n" +
                        "Baby ya, baby ya (ya)\n" +
                        "No esperes que yo responda\n" +
                        "Y s??lo dame un break, break, break\n" +
                        "Creo que t?? jodes como la ley (uh)\n" +
                        "No diga' de nuevo \"okay\"\n" +
                        "Tr??tame bien, babe\n" +
                        "As?? que baila pa' m?? (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' as??\n" +
                        "As?? que baila pa' mi (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' as??, yeah\n" +
                        "Baila pa' m?? (baila pa' mi)\n" +
                        "Me gusta la manera en que t?? lo mueve' as??\n" +
                        "Baila pa' mi (baila pa' mi)\n" +
                        "Tr??tame bien, babe (yeah)\n" +
                        "Trato, trato y queda nada\n" +
                        "Peleamos otra vez\n" +
                        "Trato, trato\n" +
                        "A veces me habla\n" +
                        "Y a veces no tambi??n (??Por qu???)\n" +
                        "Como un beb??\n" +
                        "Mami ya, mami ya\n" +
                        "Me cans?? de pelear\n" +
                        "Baby ya, baby ya\n" +
                        "No esperes que yo responda\n" +
                        "Y s??lo dame un break, break, break\n" +
                        "Creo que t?? jodes como la ley\n" +
                        "No diga' de nuevo \"okay\"\n" +
                        "Tr??tame bien, babe\n" +
                        "Yo no 'toy pa' pleitos (pleitos)\n" +
                        "Baila que yo me deleito (-leito)\n" +
                        "Al ritmo de mi canci??n\n" +
                        "Claro que tiene' raz??n\n" +
                        "Yo no voy a discutir mejor te empieza' a desvestir\n" +
                        "Pa' que te voy a mentir\n" +
                        "Ey, chica, ya\n" +
                        "Y dale, baila pa' m?? (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' as??\n" +
                        "As?? que baila pa' mi (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' as??, yeah\n" +
                        "Baila pa' m?? (baila pa' mi)\n" +
                        "Me gusta la manera en que t?? lo mueve' as??\n" +
                        "Baila pa' m?? (baila pa' mi)\n" +
                        "Eh, eh, eh, eh\n" +
                        "Oya se jeje ma lo te\n" +
                        "Nkan to ba fe ni mo fe\n" +
                        "Oya se jeje ma lo te\n" +
                        "Nkan to ba fe ni mo fe\n" +
                        "Tell me what you want\n" +
                        "Tell me what you want\n" +
                        "You know I go give you, yeah yeah\n" +
                        "If na lovin' you want\n" +
                        "If na lovin' you need\n" +
                        "You know I go give you jeje\n" +
                        "Damilola, damilola\n" +
                        "Girl your body long, no be banner\n" +
                        "Mami, ya, mami, ya\n" +
                        "Mami, dame un break, break, break\n" +
                        "Y dale baila pa' m?? (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' as??\n" +
                        "As?? que baila pa' mi (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' as??, yeah\n" +
                        "Baila pa' m?? (baila pa' mi)\n" +
                        "Me gusta la manera en que t?? lo mueve' as??\n" +
                        "Baila pa' m?? (baila pa' mi)\n" +
                        "Mr. Eazi, we make it easy\n" +
                        "(Baila pa' mi)\n" +
                        "Latino Gang\n" +
                        "Oasis\n" +
                        "Bad Bunny, baby\n" +
                        "Baila pa' mi\n" +
                        "J Balvin, baby\n" +
                        "Baila pa' mi\n" +
                        "Mr. Eazi, baby\n" +
                        "Baila pa' mi\n" +
                        "Everything, baby\n" +
                        "Baila pa' mi\n" +
                        "Yeah, yeah\n" +
                        "Yeah, yeah";
            }


            movie.setDescription(description);
            cardRowAdapter.add(movie);
        }

        mRowsAdapter.add(new ListRow(cardPresenterHeader, cardRowAdapter));

        /* Set */
        setAdapter(mRowsAdapter);
    }


    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {

        }
    }
}