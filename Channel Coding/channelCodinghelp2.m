clc
clear all
close all

%Encoding with incremental redundancy
p=0.1;
obj = VideoReader('highway.avi');
a= read(obj);
frames=get(obj,'NumberOfFrames');


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


%initializing the puncturung patterns
punPattern=[1 1 1 0 1 0 1 0 0 1 1 0 1 0 1 0;1 1 1 0 1 0 1 0 1 1 1 0 1 0 1 0;1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0;1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 0;1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1];


%encoding and puncturing with the first rate
encoded=[1:594,1:2048];
 for i=1:594
 encoded(i,1:1152) = convenc(divided(i,1:end),poly2trellis(7,[171 133]),punPattern(1,:));
 end
encoded=encoded(1:594,1:1152);




%transmitting the data over the channel
recieved = bsc(encoded,p);
numOfSentBits=1152*594;


%decoding the data with the first puncturing rate
for i=1:594
decodedrec(i,1:1024)=vitdec(recieved(i,:),poly2trellis(7,[171 133]),98,'trunc','hard',punPattern(1,1:end));
rr=xor(decodedrec(i,1:1024),divided(i,1:1024));
errorBitsWithoutRedun=errorBitsWithoutRedun+length(find(rr(1,:)==1));
end


%checking if the decoded recieved data not equal the original divided data,
%the transmitter must use the next rate for puncturung then sending agian

for i=1:594
    for j=1:5
        if divided(i,:)==decodedrec(i,:)
          if(j==2)
              numOfSentBits=numOfSentBits+128;
          end
          if(j==3)
              numOfSentBits=numOfSentBits+384;
          end
          if(j==4)
              numOfSentBits=numOfSentBits+640;
          end
    break;
        else
 encoded2=convenc(divided(i,1:end),poly2trellis(7,[171 133]),punPattern(j,:));
 recieved2 = bsc(encoded2,p);
decodedrec(i,1:1024)=vitdec(recieved2,poly2trellis(7,[171 133]),34,'trunc','hard',punPattern(j,1:end));
     if(j==5)
         numOfSentBits=numOfSentBits+896;
     end
        end
    end
rr2=xor(decodedrec(i,1:1024),divided(i,1:1024));
errorBitsWithRedun=errorBitsWithRedun+length(find(rr2(1,:)==1));    
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


%saving the new Movie
movie2avi(mov,'p=0.1 using incremental redundancy.avi')
%play the movie
implay('p=0.1 using incremental redundancy.avi')