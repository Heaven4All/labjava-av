package com.aviva.ciso.sdlc.medium.unsafeobjectbinding;

public class DTOMapper {

    static public ItemDTO convertToItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO(item.getId());
        itemDTO.setOrder(item.getOrder());
        itemDTO.setBuyerId(item.getBuyer().getId());
        itemDTO.setBuyerName(item.getBuyer().getName());
        return itemDTO;
    }
    static public Item convertFromItemDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setOrder(itemDTO.getOrder());
        return item;
    }
}
