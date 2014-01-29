<?

include_once 'src/connection.php';

$database = new dbConnection();

function displayContent()
{

    global $database;

    ?>

    <article class="post article">
        <div class="block">
            <h2 class="blockheader">Below is the list of tours on our server!</h2>

            <div class="blockcontent clearfix">
                <h4 class="comment-header"> What information and images that we provide:</h4>

                <p>
                    The list below contains the current routes that are on the server! The Map contains, the route
                    identified
                    by a red line, point of interest markers which display thumbnails which are linked to relevant
                    media.
                </p>
            </div>
            <br>

            <div class="block">
                <h2 class="blockheader">List of Tours</h2>

                <div class="blockcontent clearfix">

                    <?

                    $list = $database->getListOfTours();
                    print('<ol>');
                    foreach ($list as $tour) {

                        print('<li>');
                        print('<p>');
                        print('<a href="map.php?id=');
                        print($tour->getID());
                        print('">');
                        print($tour->getTitle());
                        print('</a><br>');
                        print($tour->getShortDesc());
                        print('</p>');
                        print('<hr>');
                        print('</li>');

                    }
                    print('</ol>');

                    ?>

                </div>
            </div>

    </article>

<?

}

require_once 'src/template.php';

