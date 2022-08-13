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
                movie.setTitle("La canción");
                movie.setStudio("Oasis");
                description = "Yeh, yeh, yeh\n" +
                        "Pensaba que te había olvidao, eh\n" +
                        "Pero pusieron la canción, eh, eh, eh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Pensaba que te había olvidao, eh\n" +
                        "Pero pusieron la canción, eh, eh, eh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Pensaba que te había olvidao\n" +
                        "Justo cuando creía\n" +
                        "Que por comerme a dos o diez, te olvidaría (yeh)\n" +
                        "Cogí un respiro y me salí de la vía\n" +
                        "Y como un pendejo, no sabía lo que hacía\n" +
                        "Nunca lo superé (no), nunca te superé (no)\n" +
                        "Hasta me aprendí to'a las baladas en inglés (yeh)\n" +
                        "Respiré y conté hasta tres\n" +
                        "Eres la fantasía oscura de Kanye West, bebé, ey\n" +
                        "Hace tiempo lo barato me salió caro (yih)\n" +
                        "Yo sólo twitteo, balas locas disparo (yih)\n" +
                        "¿Cómo olvidar la bellaquera en el carro?\n" +
                        "¿A que guió solo?\n" +
                        "Pensaba que te había olvidao (pero no), yeh\n" +
                        "Pero pusieron la canción, yeh, yeh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Pensaba que te había olvidao, eh\n" +
                        "Pero pusieron la canción, yeh, yeh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos, ey, ey\n" +
                        "Y hace tiempo que no venías a mi cabeza\n" +
                        "Pero ya van par de cervezas\n" +
                        "Y me acordé de cómo tú me besas\n" +
                        "De to' los polvos encima 'e la mesa\n" +
                        "Y en el carro, la playa, y el motel\n" +
                        "En casa de tu pai, cuando yo te iba a ver\n" +
                        "Las veces que tu mai nos llegó a coger\n" +
                        "Tú brincando mojaíta, sudando Chanel\n" +
                        "Yo sé que lo nuestro es cosa de ayer\n" +
                        "Y me pone contento que te va bien con él\n" +
                        "Yo ni te extrañaba ni te quería ver\n" +
                        "Pero pusieron la canción que te gustaba poner\n" +
                        "Y me acordé de ti, cuando me hiciste feliz\n" +
                        "Se acabó, pues, me fui, ey\n" +
                        "Yo mismo me río de mí porque\n" +
                        "Pensaba que te había olvidao, eh\n" +
                        "Pero pusieron la canción, yeh, yeh\n" +
                        "Que cantamos bien borrachos\n" +
                        "Que bailamos bien borrachos\n" +
                        "Nos besamos bien borrachos los dos\n" +
                        "Y yo pensaba que tu nombre estaba muerto, eh\n" +
                        "Pero te soñé despierto, ey\n" +
                        "Hoy salí pa' la calle suelto\n" +
                        "Sin sentimientos, el corazón desierto\n" +
                        "Y yo pensaba que tu nombre estaba muerto\n" +
                        "Pero te soñé despierto\n" +
                        "Hoy salí pa' la calle suelto\n" +
                        "Sin sentimientos, el corazón desierto";

            } else if (i == 1) {
                movie.setCardImageUrl("https://e.rpp-noticias.io/normal/2019/08/23/832044j-balvin-bad-bunny-cuidao-por-ahjpg.jpg");
                movie.setTitle("Cuidao por ahí");
                movie.setStudio("Oasis");
                description = "Leggo\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale\n" +
                        "Baby, cuida'o por ahí (por ahí)\n" +
                        "Que eso se te sale, que eso se te sale\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale\n" +
                        "Baby, cuida'o por ahí (cuida'o)\n" +
                        "Cuando el nene sale (ey, ey)\n" +
                        "El pantisito hecho un aro y se lo puso\n" +
                        "Baby, aquí me tienes, dame uso (ey)\n" +
                        "Eso ahí atrá' no' tiene confuso'\n" +
                        "Mami, deja ya el abuso, eh, eh\n" +
                        "Explota como bencina\n" +
                        "Cachorrita solo fuma Purina\n" +
                        "No sé es de Cali o de Carolina\n" +
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
                        "Dale mami, voy a ti (¿Qué fue?)\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale\n" +
                        "Baby, cuida'o por ahí (por ahí)\n" +
                        "Que eso se te sale, que eso se te sale\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale (rrr, ey)\n" +
                        "Baby, cuida'o por ahí\n" +
                        "Cuando el nene sale (ey, ey)\n" +
                        "Okay, vamo' p'al verso\n" +
                        "Tú tiene' un flow demasiado 'e perverso\n" +
                        "Y yo tengo un reggaeton demasia'o extenso\n" +
                        "To' los que te tiran sé que van pal friendzone (ey)\n" +
                        "Diabla, ¿qué es lo que tú habla'? (habla')\n" +
                        "Tú sabe' bien que te quiero dar tabla (tabla, tabla)\n" +
                        "Perro que muerde casi nunca ladra (rrr)\n" +
                        "Quiere hacer maldade' como una pobre diabla (ey)\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale voy a ti\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti (¿Qué fue?, Ey, ey)\n" +
                        "Vamo' a hacer maldade' (ey, ey)\n" +
                        "No le pare y dale (ey, ey)\n" +
                        "Baby, cuida'o por ahí (por ahí)\n" +
                        "Que eso se te sale, que eso se te sale\n" +
                        "Vamo' a hacer maldade'\n" +
                        "No le pare y dale (rrr, ey)\n" +
                        "Baby, cuida'o por ahí (cuida'o)\n" +
                        "Cuando el nene sale (ey, ey)\n" +
                        "Yo\n" +
                        "Bad Bunny, baby\n" +
                        "J Balvin, man\n" +
                        "Bad Bunny, baby\n" +
                        "Bad Bunny\n" +
                        "J Balvin, man\n" +
                        "Oasis\n" +
                        "Tainy\n" +
                        "Esto es pa' ti bebé\n" +
                        "Muévelo, muévelo\n" +
                        "Muévelo, muévelo\n" +
                        "Muévelo, muévelo\n" +
                        "Muévelo, muévelo\n" +
                        "Latino Gang\n" +
                        "Muévelo, muévelo\n" +
                        "Muévelo, muévelo\n" +
                        "Bad Bunny, J Balvin, man, Tainy\n" +
                        "Muévelo, ey\n" +
                        "(Woo)\n" +
                        "Dale mami, voy a ti\n" +
                        "Dale mami, voy a ti (ey)\n" +
                        "Dale voy a ti\n" +
                        "Yo sé que puedes, dale, voy a ti (eh eh)\n" +
                        "Dale mami, voy a ti\n" +
                        "Jaja, ey";

            } else if (i == 2) {
                movie.setCardImageUrl("https://www.informador.mx/__export/1562187820184/sites/elinformador/img/2019/07/03/balvin_crop1562187749749.jpg_1902800913.jpg");
                movie.setTitle("Odio");
                movie.setStudio("Oasis");
                description = "No quiero saber de ti (No)\n" +
                        "No sé qué haces aquí\n" +
                        "Ya yo te lo advertí (Yeh, yeh)\n" +
                        "Así que, mami, no te me ilusiones\n" +
                        "Que pa' ti ya yo no escribo canciones\n" +
                        "Lo que digan, me tiene sin cojones\n" +
                        "Mala mía, bebé, no te encojones\n" +
                        "Pero, en realidad, a ti ya yo te odio, odio (Huh)\n" +
                        "Pase de amor al odio (Odio)\n" +
                        "Que me perdone Dios, pero te odio (¡Eh!)\n" +
                        "¿Que no quiero na' contigo? Eso es obvio (No, no, no, no, no)\n" +
                        "Si quieres, te lo meto, pero será con odio, odio\n" +
                        "Pase de amor al odio (Odio)\n" +
                        "Que me perdone Dios, pero te odio (¡Eh!)\n" +
                        "¿Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero será con odio\n" +
                        "Tengo par de vídeos sexuales contigo por ahí (Sí)\n" +
                        "Tú no te merece' a nadie el día 'e San Valentín (No, no)\n" +
                        "Pa'l carajo con tu novio, quiero que no sea' feliz\n" +
                        "Y en la calle se comente lo duro que yo te di (Wuh, wuh, wuh)\n" +
                        "La calle y el trabajo (huh), y los hoteles caros (Okay)\n" +
                        "Si yo sabía cuánto ganabas, se me hizo raro (All right, all right)\n" +
                        "En un yate en Miami, al otro día en Dubai (Wuh)\n" +
                        "No me creas tan pendejo, ya eso e' lo que hay (Hay)\n" +
                        "Rencor y remordimiento, sí\n" +
                        "Lo tuyo y lo mío se fue con el viento, ey\n" +
                        "Ando por ahí con otra, en tu asiento (Sí)\n" +
                        "Ayer fuimos a comer y me gasté 500 (Ching!)\n" +
                        "Y es por el maldito odio que ahora te tengo (Yeh)\n" +
                        "Sí, voy a hacerte un velorio y de ti me vengo\n" +
                        "Tranquila, de noche, tú vas a volver (Ajá)\n" +
                        "Pidiendo que te lo haga solamente por placer y lo voy a hacer\n" +
                        "Pero para que entiendas (Okay?)\n" +
                        "Que si tienes tienda es pa' que la atiendas\n" +
                        "Te confiaste y te creíste la jodienda (La jodienda)\n" +
                        "Pa' ti ya no tengo espacio en mi agenda, baby\n" +
                        "No te sorprendas (Wuh, wuh)\n" +
                        "Creí que esto valía más que to'a mis prendas\n" +
                        "Te confiaste y te creíste la jodienda\n" +
                        "Y ahora quisiera perdonarte\n" +
                        "Pero, en realidad, a ti ya yo te odio, odio (¿Cómo?)\n" +
                        "Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio\n" +
                        "¿Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero será con odio, odio\n" +
                        "Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio\n" +
                        "¿Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto (Pero será con odio)\n" +
                        "Te deseo lo peor (Eh), que tengas mala suerte\n" +
                        "Que sufras en la vida y hasta después de la muerte (Eh)\n" +
                        "Borre tus foto', ya no quiero verte (¡No!)\n" +
                        "Lo mejor que me ha pasado fue perderte (Wu huh)\n" +
                        "Y no, no, no me busques má', no me busques má', que no estoy pa' ti\n" +
                        "No, no, no me busques má', no me busques má'\n" +
                        "Y si te trato mal (Eh), no e' intencional (No)\n" +
                        "Es que por más que tú quieras, no te puedo amar (Na')\n" +
                        "Si te trato mal (Eh), no e' intencional\n" +
                        "Es que por má' que tú quiera' arreglar\n" +
                        "Ya yo te odio, odio (Eh)\n" +
                        "Pase de amor al odio (Odio)\n" +
                        "Que me perdone Dios, pero te odio (Wuh)\n" +
                        "¿Que no quiero na' contigo? Eso es obvio (Yeh, yeh, yeh, yeh, yeh, yeh)\n" +
                        "Si quieres, te lo meto, pero será con odio, odio\n" +
                        "Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio\n" +
                        "¿Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero será con odio, oh (Na')\n" +
                        "(Pase de amor al odio\n" +
                        "Que me perdone Dios, pero te odio)\n" +
                        "(¿Que no quiero na' contigo? Eso es obvio\n" +
                        "Si quieres, te lo meto, pero será con odio, oh)\n" +
                        "(Y es por el maldito odio que ahora te tengo)";
            } else if (i == 3) {
                movie.setCardImageUrl("https://rollingstoneindia.com/wp-content/uploads/2019/06/Screen-Shot-2019-06-28-at-2.55.11-PM.png");
                movie.setTitle("Qué pretendes");
                movie.setStudio("Oasis");
                description = "Uah\n" +
                        "Uah, uah\n" +
                        "Yeah, yeah, yeah, yeah (Uh-uh)\n" +
                        "Ahora quiere' volver (Ahora quieres volver, yeah)\n" +
                        "¿Por qué razón? Dime para qué (¿Para qué, para qué?)\n" +
                        "Ya no te presto atención (Ninguna)\n" +
                        "Desde hace tiempo le puse punto final (Sí)\n" +
                        "¿Qué pretendes tú llamándome a esta hora? (Yeah, hora)\n" +
                        "Esa actitud la conozco ya (Yeah-yeah-yeah)\n" +
                        "Sabes qué hacer muy bien para envolverme (Envolverme)\n" +
                        "Pero esta vez es muy tarde ya (Uah, oh-oh-oh)\n" +
                        "Estas no son horas de llamar (No)\n" +
                        "Al meno' que me lo quiera' mamar\n" +
                        "Que quiera' prender, que quiera' quemar (Uh-huh)\n" +
                        "Hablando claro, ya tú me cae' hasta mal (Ja)\n" +
                        "Por ti me metí pastilla' y me fui de overflow Lamar (Woh)\n" +
                        "Pero tú no ere' una Kardashian (No)\n" +
                        "Contigo no me tiro, porque si no las retro se me embachan\n" +
                        "De Snapchat te borré, de Facebook te borré\n" +
                        "De Instagram te borré, de mi vida te borré (¡Plo!)\n" +
                        "Y ahora quiere' volver (¡Nah!)\n" +
                        "Nah, tú lo que quiere' e' joder (¡Wuh!)\n" +
                        "Pero no se va a poder (Fuck you)\n" +
                        "Me va' a ver con otra y te va' a morder (Uh)\n" +
                        "Y ahora quiere' volver\n" +
                        "Nah, tú lo que quiere' e' joder\n" +
                        "Pero no se va a poder\n" +
                        "Me vas a ver con otra y te va' a morder (¡Nah!)\n" +
                        "¿Qué pretendes tú llamándome a esta hora? (Ey)\n" +
                        "Esa actitud la conozco ya\n" +
                        "Sabes qué hacer muy bien para envolverme\n" +
                        "Pero esta vez es muy tarde ya\n" +
                        "Yeah-yeah-yeah-yeah-yeah-yeah\n" +
                        "Intentas hacerlo todo para que yo vuelva\n" +
                        "Las cosas no son iguales, ¿Para qué insistir?\n" +
                        "Evita molestias y tu tiempo no pierdas\n" +
                        "Conmigo no encuentras nada\n" +
                        "A escondidas vives chequeando las fotos\n" +
                        "Investigando mi perfil\n" +
                        "No lo niegues, bien te conozco\n" +
                        "Todo lo que tú hiciste conmigo\n" +
                        "Quiere' repetirlo\n" +
                        "Anda buscando más\n" +
                        "Y a mí eso me da igual\n" +
                        "Todo lo que tú hiciste conmigo\n" +
                        "Quiere' repetirlo\n" +
                        "Andas buscando más\n" +
                        "Y a mí eso me da igual\n" +
                        "¿Qué pretendes tú llamándome a esta hora?\n" +
                        "Esa actitud la conozco ya (Yeah-yeah-yeah)\n" +
                        "Sabes qué hacer muy bien para envolverme (Envolverme)\n" +
                        "Pero esta vez es muy tarde ya (Uah, oh-oh-oh)\n" +
                        "¿Qué pretendes tú llamándome a esta hora? (Ey)\n" +
                        "Esa actitud la conozco ya\n" +
                        "Sabes qué hacer muy bien para envolverme\n" +
                        "Pero esta vez es muy tarde ya\n" +
                        "Yeah-yeah-yeah-yeah-yeah-yeah\n" +
                        "Sky Rompiendo\n" +
                        "Yeah, yeah, yeah, yeah\n" +
                        "Rompiendo el bajo\n" +
                        "Bad Bunny, baby-be-be-bebé-bebé, bebé\n" +
                        "J Balvin\n" +
                        "Leggo'\n" +
                        "J Balvin\n" +
                        "Bad Bunny\n" +
                        "Oasis\n" +
                        "Oasis, baby\n" +
                        "Esta va pa' ti";
            } else {
                movie.setCardImageUrl("https://cloudfront-us-east-1.images.arcpublishing.com/gruponacion/FHAZ5XJRZFA7HFIZIHAATDZBSU.png");
                movie.setTitle("Como un bebé");
                movie.setStudio("Oasis");
                description = "Legendary Beatz\n" +
                        "Yeah, Oasis\n" +
                        "Leggo\n" +
                        "Leggo\n" +
                        "Trato, trato y queda nada\n" +
                        "Peleamos otra vez (otra vez, otra vez, otra vez, otra vez)\n" +
                        "Trato, trato a veces me habla\n" +
                        "Y a veces no también (¿Por qué?)\n" +
                        "Como un bebé\n" +
                        "Mami ya, mami ya (ya)\n" +
                        "Me cansé de pelear (no)\n" +
                        "Baby ya, baby ya (ya)\n" +
                        "No esperes que yo responda\n" +
                        "Y sólo dame un break, break, break\n" +
                        "Creo que tú jodes como la ley (uh)\n" +
                        "No diga' de nuevo \"okay\"\n" +
                        "Trátame bien, babe\n" +
                        "Así que baila pa' mí (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' así\n" +
                        "Así que baila pa' mi (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' así, yeah\n" +
                        "Baila pa' mí (baila pa' mi)\n" +
                        "Me gusta la manera en que tú lo mueve' así\n" +
                        "Baila pa' mi (baila pa' mi)\n" +
                        "Trátame bien, babe (yeah)\n" +
                        "Trato, trato y queda nada\n" +
                        "Peleamos otra vez\n" +
                        "Trato, trato\n" +
                        "A veces me habla\n" +
                        "Y a veces no también (¿Por qué?)\n" +
                        "Como un bebé\n" +
                        "Mami ya, mami ya\n" +
                        "Me cansé de pelear\n" +
                        "Baby ya, baby ya\n" +
                        "No esperes que yo responda\n" +
                        "Y sólo dame un break, break, break\n" +
                        "Creo que tú jodes como la ley\n" +
                        "No diga' de nuevo \"okay\"\n" +
                        "Trátame bien, babe\n" +
                        "Yo no 'toy pa' pleitos (pleitos)\n" +
                        "Baila que yo me deleito (-leito)\n" +
                        "Al ritmo de mi canción\n" +
                        "Claro que tiene' razón\n" +
                        "Yo no voy a discutir mejor te empieza' a desvestir\n" +
                        "Pa' que te voy a mentir\n" +
                        "Ey, chica, ya\n" +
                        "Y dale, baila pa' mí (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' así\n" +
                        "Así que baila pa' mi (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' así, yeah\n" +
                        "Baila pa' mí (baila pa' mi)\n" +
                        "Me gusta la manera en que tú lo mueve' así\n" +
                        "Baila pa' mí (baila pa' mi)\n" +
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
                        "Y dale baila pa' mí (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' así\n" +
                        "Así que baila pa' mi (baila pa' mi)\n" +
                        "Me gusta la manera cuando me lo mueve' así, yeah\n" +
                        "Baila pa' mí (baila pa' mi)\n" +
                        "Me gusta la manera en que tú lo mueve' así\n" +
                        "Baila pa' mí (baila pa' mi)\n" +
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