package mashup.spring.jsmr.adapter.api.weddingChannel;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.weddingChannel.dto.ParticipateWeddingChannelResponseDTO;
import mashup.spring.jsmr.adapter.api.weddingChannel.dto.WeddingChannelGuestResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.weddingChannel.WeddingChannelApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequestMapping("/api/v1/wedding")
@RequiredArgsConstructor
@RestController
public class WeddingChannelController {

    private final WeddingChannelApplicationService weddingChannelApplicationService;

    @ApiOperation("결혼식에 참석하는 소개팅 리스트 조회")
    @GetMapping("/guests")
    public ApiResponse<List<WeddingChannelGuestResponseDTO>> getWeddingGuests(@ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, weddingChannelApplicationService.getWeddingGuests(userId));
    }

    @ApiOperation("웨딩코드로 결혼식에 참여")
    @PostMapping("/{weddingCode}")
    public ApiResponse<ParticipateWeddingChannelResponseDTO> participateWeddingChannel(@ApiIgnore @LoginUserId Long userId, @PathVariable String weddingCode){
        return ApiResponse.success(HttpStatus.OK,weddingChannelApplicationService.participateWeddingChannel(userId, weddingCode));
    }
}
