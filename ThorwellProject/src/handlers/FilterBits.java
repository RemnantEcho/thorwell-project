/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

/**
 *
 * @author jly09
 */

public class FilterBits {
    public static final short GROUND_CATEGORY_BITS = 0x0001;
    public static final short PLAYER_CATEGORY_BITS = 0x0002;
    public static final short PASSABLE_CATEGORY_BITS = 0x0004;
    public static final short ITEMS_CATEGORY_BITS = 0x0008;
    public static final short DYNAMIC_CATEGORY_BITS = 0x0016;

    public static final short GROUND_MASK_BITS = PLAYER_CATEGORY_BITS | ITEMS_CATEGORY_BITS | DYNAMIC_CATEGORY_BITS;
    public static final short PLAYER_MASK_BITS = GROUND_CATEGORY_BITS | PLAYER_CATEGORY_BITS | DYNAMIC_CATEGORY_BITS;
    public static final short PASSABLE_MASK_BITS = DYNAMIC_CATEGORY_BITS;
    public static final short ITEMS_MASK_BITS = PLAYER_CATEGORY_BITS | PLAYER_CATEGORY_BITS | DYNAMIC_CATEGORY_BITS;
    public static final short DYNAMIC_MASK_BITS = GROUND_CATEGORY_BITS | PLAYER_CATEGORY_BITS | PASSABLE_CATEGORY_BITS | ITEMS_CATEGORY_BITS | DYNAMIC_CATEGORY_BITS;
}