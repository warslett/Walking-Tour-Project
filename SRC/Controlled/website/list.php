<?

include_once 'connection.php';

$database = new dbConnection();

function displayContent() {
    
    global $database;
    
    ?>

    <article class="post article">
        
        <h2 class="postheader">List of Tours</h2>
        
        <div class="postcontent postcontent-0 clearfix">
            
            <?
            
            $list=$database->getListOfTours();
            
            foreach($list as $tour){
                
                print("<p>");
                print('<a href="map.php?id=');
                print($tour->getID());
                print('">');
                print($tour->getTitle());
                print('</a><br>');
                print($tour->getShortDesc());
                print("</p>");
                print("<hr>");
                
            }
            
            ?>
            
        </div>
        
    </article>

        <?
        
}

require_once 'template.php';
