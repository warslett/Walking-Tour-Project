<!DOCTYPE html>

<html dir="ltr" lang="en-US" xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />

        <title>Group 5: Walking Tour Creator</title>
        <meta content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width" name="viewport" /><!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
        <link href="src/style.css" media="screen" rel="stylesheet" /><!--[if lte IE 7]>
        <link rel="stylesheet" href="src/style.ie7.css" media="screen"/><![endif]-->
        <link href="src/style.responsive.css" media="all" rel="stylesheet" />
        <script src="src/jquery.js"></script>
        <script src="src/script.js"></script>
        <script src="src/script.responsive.js"></script>
        <?
        if(isset($header)){
            echo $header;
        }
        ?>
    </head>

    <body>
        <div id="main">
            <header class="header">
                <div class="shapes">
                    <div class="object1388086240"></div>

                    <div class="textblock object557410578">
                        <div class="object557410578-text-container">
                            <div class="object557410578-text"></div>
                        </div>
                    </div>
                </div>

                <h1 class="headline"><a href="#">Group 05</a></h1>

                <h2 class="slogan">Walking Tour Creator</h2>
            </header>

            <nav class="nav">
                <div class="nav-inner">
                    <ul class="hmenu">
                        <li><a class="" href="index.php">Home</a></li>

                        <li><a class="" href="list.php">List of Tours</a></li>

                        <li><a href="about.php">About</a></li>
                    </ul>
                </div>
            </nav>

            <div class="sheet clearfix">
                <div class="layout-wrapper">
                    <div class="content-layout">
                        <div class="content-layout-row">
                            <div class="layout-cell sidebar1">
                                <div class="block clearfix">
                                    <div class="blockheader">
                                        <h3 class="t">Reasons to Travel Green</h3>
                                    </div>

                                    <div class="blockcontent">
                                        <ol>
                                            <li>Vivamus bibendum nibh sit amet enim fermentum vehicula. <a href="#">more</a></li>

                                            <li>Cum sociis natoque penatibus et magnis dis parturient montes. <a href="#">more</a></li>

                                            <li>Nascetur ridiculus mus. In eget viverra libero. <a href="#">more</a></li>

                                            <li>Duis nisi urna, placerat id vehicula at, rhoncus vel eros. <a href="#">more</a></li>

                                            <li>Proin pellentesque rhoncus suscipit. <a href="#">more</a></li>

                                            <li>Aliquam diam tortor, dictum nec iaculis sed, dignissim et mauris. <a href="#">more</a></li>
                                        </ol>
                                    </div>
                                </div>
                            </div>

                            <div class="layout-cell content">
                                <?
                                displayContent();
                                ?>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <div class="footer-inner">
                    <p><span style="font-size: 13px; font-weight: bold;"><br /></span></p>

                    <p><br /></p>

                    <p class="page-footer"><span id="footnote-links">Designed by <a href="polyspotstudios.co.uk" target="_blank">Stephen Clasby</a>.</span></p>
                </div>
            </footer>
        </div>
        <?
        if(isset($footer)){
            echo $footer;
        }
        ?>
    </body>
</html>