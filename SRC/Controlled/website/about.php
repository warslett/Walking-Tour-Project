<?php
require_once 'src/template.php';
function displayContent()
{
    ?>

    <article class="block article">
        <h2 class="blockheader">About this Project</h2>

        <div class="clearfix block">
            <p class="blockcontent">

                The android application works using a GPS or mobile data connection to determine the user’s location.
                When started, a new walk consists of an initial point and from then on, coordinate tracking begins.
                The user is able to set the distance at which their location is confirmed and added to the walk.
                Aside from that users can add points of interest along the walk.

                These points can have a description attached to them as well as up to 5 photos.
                When the walk is finished, it is uploaded to the server and stored so it can be later viewed on the map
                here on the site. On the map, points of interest appear as pins. Clicking them will reveal further
                information and photos, if any.

            </p>

            <h2 class="blockheader">Group 05 Project Members:</h2>

            <p class="blockcontent">
                <ul class="block">
                    <li><strong>Web Development Team:</strong> <em>William Arslett & Stephen Paul Clasby</em></li>
                    <li><strong> Android Development Team:</strong> <em>Harvey Clark & William Lea</em></li>
                    <li><strong>Quality assurance and Testing:</strong> <em>Neil Vicker & Benjamin O'Donovan</em></li>
                    <li><strong>Deputy project leader:</strong> <em>Benjamin O’Donovan</em></li>
                <li><strong>Project leader:</strong> <em>Sevastiyan Raychev</em>li>
                </ul>


            <h3><strong>Contact e-mail address:</strong><em><a href="cs-group-05@aber.ac.uk">cs-group-05@aber.ac.uk</a>
                </em></h3>

            </p>


        </div>
    </article>

<?php
}

?>