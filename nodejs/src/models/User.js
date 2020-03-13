const mongoose = require('mongoose');
const mongoosePaginate = require('mongoose-paginate');

const UserSchema = new mongoose.Schema({
    id: { 
        type: mongoose.Types.ObjectId, 
        required: true,
        auto: true,
    },
    name: {
        type: String,
        required: true,
    },
    username: {
        type: String,
        required: true,
    },
    createdAt: {
        type: Date,
        default: Date.now,
    },
    modifiedAt: {
        type: Date,
        default: Date.now,
    }
});

UserSchema.plugin(mongoosePaginate);

var User = mongoose.model('User', UserSchema);