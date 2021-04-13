Addition of the particularity of the beginning of the game.

Choice: 1 (Online version on Board Game Arena: At the start of the game, everyone receives 4 tiles which are automatically placed on the diagonal in ascending order)

Addition of 2 methods: List <Tile> fourRandomTile () and int random ()

List <Tile> fourRandomTile (): create a list of 4 tiles with a random value between 1 and 20. This list is sorted in ascending order. This method uses int random () to generate the value of the tile.

int random (): Returns a random number between 1 and 20 (included)

Modification of the constructor, it constructs an empty 2-dimensional array of size 4v4 then calls the fourRandomTile () method and appends its values on the descending diagonal.

The model interface has not been modified