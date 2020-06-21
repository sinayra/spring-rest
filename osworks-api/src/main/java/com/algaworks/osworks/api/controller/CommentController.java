package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.algaworks.osworks.api.model.CommentInputModel;
import com.algaworks.osworks.api.model.CommentModel;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Comment;
import com.algaworks.osworks.domain.service.ServiceOrderService;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-order/{serviceOrderId}/comments")
public class CommentController {

    @Autowired
    ServiceOrderService serviceOrderService;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModel create(@PathVariable Long serviceOrderId,
            @Valid @RequestBody CommentInputModel commentInputModel) {

        Comment comment = serviceOrderService.create(serviceOrderId, commentInputModel.getDescription());

        return toModel(comment);
    }

    @GetMapping
    public List<CommentModel> index(@PathVariable Long serviceOrderId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Service order not found"));

        List<Comment> comments = serviceOrder.getComments();
        return toListModel(comments);

    }

    private CommentModel toModel(Comment comment) {
        return modelMapper.map(comment, CommentModel.class);
    }

    private List<CommentModel> toListModel(List<Comment> comments) {
        return comments.stream().map(comment -> toModel(comment)).collect(Collectors.toList());
    }

    /*
     * private CommentModel toEntity(Comment comment) { return
     * modelMapper.map(comment, CommentModel.class); }
     */

}