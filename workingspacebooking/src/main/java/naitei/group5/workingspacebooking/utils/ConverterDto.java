package naitei.group5.workingspacebooking.utils;

import naitei.group5.workingspacebooking.dto.request.CreateVenueRequestDto;
import naitei.group5.workingspacebooking.dto.response.VenueResponseDto;
import naitei.group5.workingspacebooking.entity.User;
import naitei.group5.workingspacebooking.entity.Venue;
import naitei.group5.workingspacebooking.entity.VenueStyle;

public class ConverterDto {

    public static Venue toVenueEntity(CreateVenueRequestDto requestDto, User owner, VenueStyle venueStyle) {
        return Venue.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .capacity(requestDto.getCapacity())
                .location(requestDto.getLocation())
                .image(requestDto.getImage())
                .verified(false) 
                .owner(owner)
                .venueStyle(venueStyle)
                .build();
    }

    public static VenueResponseDto toVenueResponseDto(Venue venue) {
        return VenueResponseDto.builder()
                .id(venue.getId())
                .name(venue.getName())
                .description(venue.getDescription())
                .capacity(venue.getCapacity())
                .location(venue.getLocation())
                .image(venue.getImage())
                .verified(venue.getVerified())
                .venueStyleName(venue.getVenueStyle() != null ? venue.getVenueStyle().getName() : null)
                .ownerName(venue.getOwner() != null ? venue.getOwner().getName() : null)
                .build();
    }

    public static void updateVenueFromDto(Venue venue, CreateVenueRequestDto requestDto, VenueStyle venueStyle) {
        venue.setName(requestDto.getName());
        venue.setDescription(requestDto.getDescription());
        venue.setCapacity(requestDto.getCapacity());
        venue.setLocation(requestDto.getLocation());
        venue.setImage(requestDto.getImage());
        
        if (venueStyle != null) {
            venue.setVenueStyle(venueStyle);
        }
    }

    private ConverterDto() {
        throw new UnsupportedOperationException("Utility class không thể được khởi tạo");
    }
}
