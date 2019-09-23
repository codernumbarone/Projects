clc
clear all
close all

%Encoding with rate 1/2 (No puncturing)
p=0.001;
obj = VideoReader('highway.avi');
a= read(obj);
frames=get(obj,'NumberOfFrames');


for l=1:6
    
if(l==1)
    p=0.0001;    
end
if(l>1)
    p=p+0.03998;
end
%extracting Frames

for i=1:frames
     I(i).cdata=a(:,:,:,i);
end   

s=size(I(1).cdata);
mov(1:frames) =struct('cdata', zeros(s(1),s(2), 3, 'uint8'),'colormap', []);

for Frame=1:frames
    errorBitsWithoutRedun=0;
    errorBitsWithRedun=0;
    numOfSentBits=0;
    
%Red Components of the Frame
R=I(Frame).cdata(:,:,1); 
%Green Components of the Frame
G=I(Frame).cdata(:,:,2); 
%Blue Components of the Frame
B=I(Frame).cdata(:,:,3); 


%reshaping and converting to binary as in the project guidelines
sequenceRed=[1:25344];
sequenceRed=reshape(R,1,[]);

sequenceGreen=[1:25344];
sequenceGreen=reshape(G,1,[]);

sequenceBlue=[1:25344];
sequenceBlue=reshape(B,1,[]);

sequenceRed=double(sequenceRed);
sequenceRed=de2bi(sequenceRed);
sequenceRed=reshape(sequenceRed,1,[]);

sequenceGreen=double(sequenceGreen);
sequenceGreen=de2bi(sequenceGreen);
sequenceGreen=reshape(sequenceGreen,1,[]);

sequenceBlue=double(sequenceBlue);
sequenceBlue=de2bi(sequenceBlue);
sequenceBlue=reshape(sequenceBlue,1,[]);

sequence=horzcat(sequenceRed,sequenceGreen,sequenceBlue);


%dividing the sequence into frames
divided=[1:594,1:1024];
divided=reshape(sequence,[],1024);


%encoding 
encoded=[1:594,1:2048];
 for i=1:594
 encoded(i,1:2048) = convenc(divided(i,1:end),poly2trellis(7,[171 133]));
 end


%transmitting the data over the channel
for i=1:594
recieved(i,1:2048) = bsc(encoded(i,1:2048),p);
end

decodedrec=[1:594,1:1024];
%decoding 
for i=1:594
decodedrec(i,1:1024)=vitdec(recieved(i,1:2048),poly2trellis(7,[171 133]),98,'trunc','hard');
rr2=xor(decodedrec(i,1:1024),divided(i,1:1024));
errorBitsWithoutRedun=errorBitsWithoutRedun+length(find(rr2(1,:)==1));    
end


%collect the divided frames to one sequence again
sequenceDone=reshape(decodedrec,1,[]);


%reshaping and converting to uint8 as in the project guidelines
sequenceRedDone=sequenceDone(1,1:202752);
sequenceGreenDone=sequenceDone(1,202753:405504);
sequenceBlueDone=sequenceDone(1,405505:608256);

sequenceRedDone=reshape(sequenceRedDone,25344,8);
sequenceRedDone=bi2de(sequenceRedDone);
sequenceRedDone=uint8(sequenceRedDone);
sequenceRedDone=reshape(sequenceRedDone,144,176);


sequenceGreenDone=reshape(sequenceGreenDone,25344,8);
sequenceGreenDone=bi2de(sequenceGreenDone);
sequenceGreenDone=uint8(sequenceGreenDone);
sequenceGreenDone=reshape(sequenceGreenDone,144,176);


sequenceBlueDone=reshape(sequenceBlueDone,25344,8);
sequenceBlueDone=bi2de(sequenceBlueDone);
sequenceBlueDone=uint8(sequenceBlueDone);
sequenceBlueDone=reshape(sequenceBlueDone,144,176);



%Converting back to Video

%constracting frame back

mov(1,Frame).cdata(:,:,1) = sequenceRedDone; 
mov(1,Frame).cdata(:,:,2) = sequenceGreenDone;
mov(1,Frame).cdata(:,:,3) = sequenceBlueDone;
end
errorBitProbWithoutRed(l)=errorBitsWithoutRedun/(1024*594);

S=sprintf('done');
disp(S)
end
probVector=[0.0001 0.04008 0.08006 0.12004 0.16002 0.2]; 
figure (2)
plot(probVector,errorBitProbWithoutRed)
title('using redundency')
xlabel('Error probability') 
ylabel('Bit error probability') 


%saving the new Movie
movie2avi(mov,'Saved_Filename.avi')
%play the movie
implay('Saved_Filename.avi')

